package com.interswitch.Bookstore.service.Services;

import com.interswitch.Bookstore.service.Dtos.Responses.AddToCartResponse;
import com.interswitch.Bookstore.service.Dtos.Responses.ViewCartResponse;
import com.interswitch.Bookstore.service.Enums.PaymentStatus;
import com.interswitch.Bookstore.service.Exceptions.BookExceptions.BookNotFoundException;
import com.interswitch.Bookstore.service.Exceptions.BookInventoryExceptions.InsufficientQuantityException;
import com.interswitch.Bookstore.service.Interfaces.CartServiceInterface;
import com.interswitch.Bookstore.service.Models.Book;
import com.interswitch.Bookstore.service.Models.BookCartItem;
import com.interswitch.Bookstore.service.Models.Cart;
import com.interswitch.Bookstore.service.Models.User;
import com.interswitch.Bookstore.service.Repositories.CartRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.interswitch.Bookstore.service.Utils.BookHelperMethods.totalAmountOfBooks;

/**
 * The Cart service.
 *
 * @author Nelson Ekpenyong
 */
@Service
public class CartService implements CartServiceInterface {

   private final CartRepository cartRepository;
   private final UserService userService;
   private final BookService bookService;

   private final BookInventoryService bookInventoryService;


   /**
    * Instantiates a new CartService and injects the CartRepository, UserService, BookService, and BookInventoryService into it.
    *
    * @param cartRepository       the cart repository
    * @param userService          the user service
    * @param bookService          the book service
    * @param bookInventoryService the book inventory service
    */
    public CartService(BookService bookService, UserService userService, CartRepository cartRepository, BookInventoryService bookInventoryService) {
      this.bookService          = bookService;
      this.cartRepository       = cartRepository;
      this.userService          = userService;
      this.bookInventoryService = bookInventoryService;
   }



   /**
    * Add to cart.
    *
    * @param user The user object.
    * @return The user associated with a cart.
    */
    @Override
    public Cart getCartByUser(User user){
      return cartRepository.findByUser(user);
   }



   /**
    * Save cart: Encapsulating the CartRepository save functionality.
    *
    * @param cart The cart object to be persisted.
    * @return The persisted cart object.
    */
    public Cart saveCart(Cart cart){
      return cartRepository.save(cart);
   }



   /**
    * Add to cart.
    *
    * @param userId The user id.
    * @param bookIds The name
    * @return The persisted cart object.
    */
    //    @Override
//    public AddToCartResponse addToCart(Long userId, List<Long> bookIds) {
//
//      User user = userService.getUser(userId);
//
//      List<Book> books = bookService.getAllBooksById(bookIds);
//
//      if (books.isEmpty()) throw new BookNotFoundException("No books found with the given IDs");
//
//      Cart cart = getCartByUser(user);
//
//      if(cart == null){
//         cart = new Cart( user, new ArrayList<>(books), totalAmountOfBooks(books), PaymentStatus.PENDING );
//      }else{
//         cart.getBooks().addAll(books);
//         cart.setTotalAmount(cart.getTotalAmount() + totalAmountOfBooks(books));
//      }
//      saveCart(cart);
//
//      return new AddToCartResponse(user, books, cart.getTotalAmount(), PaymentStatus.PENDING);
//   }





//   @Override
//   public void addToCart(Long userId, List<Long> bookIds, List<Integer> quantities) {
//      User user = userService.getUser(userId); //✔️
//
//
//     if (bookIds.size() != quantities.size()) {
//         throw new IllegalArgumentException("Mismatch between bookIds and quantities lists");
//      }
//
//      Cart cart = new Cart(user, new ArrayList<>(), PaymentStatus.PENDING);
//      System.out.println("THE CART:::::" + cart);
//      cart = saveCart(cart);
//
//      List<BookCartItem> bookItems = new ArrayList<>();
//
//      for (int i = 0; i < bookIds.size(); i++) {
//         Long bookId = bookIds.get(i);
//         int quantity = quantities.get(i);
//
//
//         Book book = bookService.book(bookId);
//
//         if (book == null) {
//            throw new BookNotFoundException("Book not found with ID: " + bookId);
//         }
//
//
//         BookCartItem item = new BookCartItem(book, cart, quantity);
//         System.out.println("ITEM:::::::::::::" + item);
//
//         item.setCart(new Cart(user, Collections.singletonList(item), PaymentStatus.PENDING));
//
////         System.out.printf("ITEM AFTER::::::", item);
////         bookItems.add(item);
////         System.out.println("THE BOOK ITEMS AFTER::::::" + bookItems);
//
//      }
//
//      cart.setBookItems(bookItems);
//
//      saveCart(cart);
//
//
////      return new AddToCartResponse(user, cart.getBookItems(), cart.getTotalAmount(), PaymentStatus.PENDING);
//   }


   public void addToCart(Long userId, List<Long> bookIds, List<Integer> quantities) {
      User user = userService.getUser(userId); //✔️

      Cart cart = new Cart(user, new ArrayList<>(), PaymentStatus.PENDING);
      cart = saveCart(cart);
      Cart usersCart = getCartByUser(user); //✔

      System.out.println("THE USERS CART:::::" + usersCart);

      if (bookIds.size() != quantities.size()) {
         throw new IllegalArgumentException("Book IDs and quantities must have the same size.");
      }

      for (int i = 0; i < bookIds.size(); i++) {
         Long bookId      = bookIds.get(i);
         Integer quantity = quantities.get(i);
         Book book        = bookService.book(bookId);

         Integer availableQuantity = bookInventoryService.getBookQuantity(bookId);

         if (availableQuantity == null || availableQuantity < quantity) {
            throw new InsufficientQuantityException(bookId, quantity, availableQuantity, "Insufficient quantity in stock.");
         }

         BookCartItem bookItem = new BookCartItem(book, usersCart, quantity);
         usersCart.getBookItems().add(bookItem);
         BookCartItem bookCartItem = null;

         for (BookCartItem item : usersCart.getBookItems()) {
            if (item.getBook().getId().equals(bookId)) {
               bookCartItem = item;
               break;
            }
         }

         if (bookCartItem == null) {
             bookCartItem  = new BookCartItem(book, usersCart, quantity);
             usersCart.getBookItems().add(bookCartItem);
         }

         // Update quantity in cart (only needed if item already existed)
//         if (bookCartItem.getQuantity() != quantity) {
//            bookCartItem.setQuantity(quantity);
//         }
//         System.out.println("THE BOOK CART ITEMS:::::" + bookCartItem);

         // Update quantity in cart
//         bookCartItem.setQuantity(bookCartItem.getQuantity() + quantity);
         saveCart(cart);

         bookInventoryService.updateBookQuantity(bookId, availableQuantity - quantity);
      }


   }

   /**
    * Add to cart.
    *
    * @param userId The user id.
    * @return The book object containing the list of books.
    */
    @Override
    public ViewCartResponse viewCart(Long  userId) {
      List<Cart> carts = cartRepository.findByUser_Id(userId);
      List<BookCartItem> bookItems  = carts.stream()
                              .flatMap(cart -> cart.getBookItems().stream())
                              .collect(Collectors.toList());

      return new ViewCartResponse(bookItems);
   }

}
