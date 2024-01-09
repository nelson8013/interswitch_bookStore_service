package com.interswitch.Bookstore.service.Services;

import com.interswitch.Bookstore.service.Dtos.Requests.Cart.CartRequest;
import com.interswitch.Bookstore.service.Dtos.Responses.AddToCartResponse;
import com.interswitch.Bookstore.service.Dtos.Responses.ViewCartResponse;
import com.interswitch.Bookstore.service.Enums.PaymentStatus;
import com.interswitch.Bookstore.service.Exceptions.BookExceptions.BookNotFoundException;
import com.interswitch.Bookstore.service.Exceptions.BookInventoryExceptions.BookInventoryNotFoundException;
import com.interswitch.Bookstore.service.Exceptions.CartExceptions.CartNotFoundException;
import com.interswitch.Bookstore.service.Interfaces.CartServiceInterface;
import com.interswitch.Bookstore.service.Models.*;
import com.interswitch.Bookstore.service.Repositories.CartBookRepository;
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

   private final CartBookRepository cartbookrepository;
   private final UserService userService;
   private final BookService bookService;

   private final BookInventoryService bookInventoryService;


   /**
    * Instantiates a new CartService and injects the CartRepository, UserService, BookService, and BookInventoryService into it.
    *
    * @param bookService          the book service
    * @param userService          the user service
    * @param cartRepository       the cart repository
    * @param cbr
    * @param bookInventoryService the book inventory service
    */
    public CartService(BookService bookService, UserService userService, CartRepository cartRepository, CartBookRepository cartbookrepository, BookInventoryService bookInventoryService) {
      this.bookService          = bookService;
      this.cartRepository       = cartRepository;
      this.userService          = userService;
       this.cartbookrepository = cartbookrepository;
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
       Cart cart = cartRepository.findByUser(user);

       if(Objects.isNull(cart)){
          cart = new Cart();
          cart.setUser(user);
       }
      return cart;
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

   @Override
   public AddToCartResponse addToCart( CartRequest request) {

      User user = userService.getUser(request.getUserId());

      Map<Long, Long> bookDetail = request.getBooksAndQuantities()
              .stream().collect(Collectors.toMap( book-> book.getBookId(), book-> book.getQuantity(), (a,b) -> a ));


      List<Book> books = bookService.getAllBooksById(bookDetail.keySet());

      Cart cart = getCartByUser(user);

      if (books.isEmpty()) throw new BookNotFoundException("No books found with the given IDs");

      if (books.size() !=  bookDetail.keySet().size()) throw new IllegalArgumentException("Mismatch between bookIds and quantities lists");

      List<CartBook> cartBooks = new ArrayList<>();

      for (int i = 0; i < books.size(); i++) {
         Book book = books.get(i);

         if(!bookDetail.containsKey(book.getId())){
            continue;
         }
         Long requestedQuantity =   bookDetail.get(book.getId());

         if (!bookInventoryService.doesBookExist(book.getId()) || bookInventoryService.getBookQuantity(book.getId()) < requestedQuantity) {
            throw new BookInventoryNotFoundException("The requested book with ID: " + book.getId() + " is not available in sufficient quantity.");
         }

         CartBook cartBook = new CartBook(book, cart, requestedQuantity.intValue());
         cartBooks.add(cartBook);
     }

      cart.getCartbooks().addAll(cartBooks);

      cart.setTotalAmount(totalAmountOfBooks(cart.getCartbooks()));
      saveCart(cart);
      cartbookrepository.saveAll(cartBooks);

      return new AddToCartResponse(user, books, cart.getTotalAmount(), PaymentStatus.PENDING);
   }



   /**
    * View to cart.
    *
    * @param userId The user id.
    * @return The book object containing the list of books.
    */
    @Override
    public ViewCartResponse viewCart(Long  userId) {
       Cart cart = cartRepository.findByUserIdWithBooks(userId).orElseThrow( ()-> new CartNotFoundException("Cart not found") );

      return new ViewCartResponse(cart.getCartbooks(), cart.getTotalAmount(), cart.isActive(), cart.getCreatedAt());
   }

}
