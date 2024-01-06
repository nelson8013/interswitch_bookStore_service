package com.interswitch.Bookstore.service.Services;

import com.interswitch.Bookstore.service.Dtos.Responses.AddToCartResponse;
import com.interswitch.Bookstore.service.Dtos.Responses.ViewCartResponse;
import com.interswitch.Bookstore.service.Enums.PaymentStatus;
import com.interswitch.Bookstore.service.Exceptions.BookExceptions.BookNotFoundException;
import com.interswitch.Bookstore.service.Interfaces.CartServiceInterface;
import com.interswitch.Bookstore.service.Models.Book;
import com.interswitch.Bookstore.service.Models.Cart;
import com.interswitch.Bookstore.service.Models.User;
import com.interswitch.Bookstore.service.Repositories.CartRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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


   /**
    * Instantiates a new Cart service and injects the BookService, UserService and CartRepository into it.
    *
    * @param bookService    the book service
    * @param userService    the user service
    * @param cartRepository the cart repository
    */
    public CartService(BookService bookService, UserService userService, CartRepository cartRepository) {
      this.bookService     = bookService;
      this.cartRepository  = cartRepository;
      this.userService     = userService;
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
    @Override
    public AddToCartResponse addToCart(Long userId, List<Long> bookIds) {

      User user = userService.getUser(userId);

      List<Book> books = bookService.getAllBooksById(bookIds);

      if (books.isEmpty()) throw new BookNotFoundException("No books found with the given IDs");

      Cart cart = getCartByUser(user);

      if(cart == null){
         cart = new Cart( user, new ArrayList<>(books), totalAmountOfBooks(books), PaymentStatus.PENDING );
      }else{
         cart.getBooks().addAll(books);
         cart.setTotalAmount(cart.getTotalAmount() + totalAmountOfBooks(books));
      }
      saveCart(cart);

      return new AddToCartResponse(user, books, cart.getTotalAmount(), PaymentStatus.PENDING);
   }


   /**
    * Add to cart.
    *
    * @param userId The user id.
    * @return The book object containing the list of books.
    */
    @Override
    public ViewCartResponse viewCart(Long  userId) {
      List<Cart> carts = cartRepository.findByUserIdWithBooks(userId);
      List<Book> books = carts.stream()
                              .flatMap(cart -> cart.getBooks().stream())
                              .collect(Collectors.toList());

      return new ViewCartResponse(books);
   }

}
