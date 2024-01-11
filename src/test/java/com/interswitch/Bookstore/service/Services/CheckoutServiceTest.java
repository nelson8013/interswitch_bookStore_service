package com.interswitch.Bookstore.service.Services;

import com.interswitch.Bookstore.service.Enums.BookGenre;
import com.interswitch.Bookstore.service.Enums.PaymentOptions;
import com.interswitch.Bookstore.service.Enums.PaymentStatus;
import com.interswitch.Bookstore.service.Exceptions.CartExceptions.CartNotFoundException;
import com.interswitch.Bookstore.service.Exceptions.UserExceptions.UserNotFoundException;
import com.interswitch.Bookstore.service.Models.Book;
import com.interswitch.Bookstore.service.Models.Cart;
import com.interswitch.Bookstore.service.Models.CartBook;
import com.interswitch.Bookstore.service.Models.User;
import com.interswitch.Bookstore.service.Repositories.CartRepository;
import com.interswitch.Bookstore.service.Repositories.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CheckoutServiceTest {

   @Mock
   private CheckoutService checkoutService;

   @Mock
   private CartService cartService;

   private AutoCloseable autoCloseable;

   @Mock
   private UserService userService;

   @Mock
   private BookInventoryService bookInventoryService;

   private PaymentOptions paymentOptions;

   private User user;
   private Cart cart;

   private List<Book> books;
   private List<CartBook> cartBookList;


   private Book chinaTown;
   private Book westernCanada;
   private Book theGreatGatsby;

   Long userId;
   Long cartId;
   Long chinaTownId;
   Long westernCanadaId;
   Long theGreatGatsbyId;

   @BeforeEach
   void setUp() {
      autoCloseable = MockitoAnnotations.openMocks(this);

      userId = 1l;
      user  = new User(userId, "John", "Doe", "johndoe@gmail.com","12345" );


      chinaTown       = new Book(chinaTownId, "china Town",          BookGenre.FICTION, "789-0-4341-1348-0", "Jackie Chan", "1998", 350.0);
      westernCanada   = new Book(westernCanadaId,"Western Canada",   BookGenre.THRILLER, "789-0-4341-1347-1", "Jack Altman", "1992", 750.0);
      theGreatGatsby  = new Book(theGreatGatsbyId,"The Great Gatsby",BookGenre.FICTION, "978-0-7432-7356-5", "F. Scott Fitzgerald", "1925", 300.0);

      books = Arrays.asList(chinaTown, theGreatGatsby, westernCanada);

      cartBookList    = new ArrayList<>();
      cartBookList.add( new CartBook(books.get(0), cart, 3));
      cartBookList.add( new CartBook(books.get(1), cart, 3));
      cartBookList.add( new CartBook(books.get(2), cart, 3));


      cartId = 2l;
      cart            = new Cart(cartId, user, cartBookList, 0.00,  PaymentStatus.PENDING, false);

      checkoutService = new CheckoutService(cartService, userService,  bookInventoryService);
      cartService.saveCart(cart);

   }

   @AfterEach
   void tearDown() {
      try {
         autoCloseable.close();
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   @Test
   void checkout() {
      when(userService.getUser(user.getId())).thenReturn(user);
      when( cartService.getCartByUser(user)).thenReturn(cart);

       checkoutService.checkout(userId, PaymentOptions.WEB);

       assertEquals(PaymentStatus.SUCCESS, cart.getPaymentStatus());
   }

   @Test
   void checkout_CartNotFound() {
      when(userService.getUser(userId)).thenReturn(user);
      when(cartService.getCartByUser(user)).thenReturn(null);

      assertThrows(CartNotFoundException.class, () -> checkoutService.checkout(userId, PaymentOptions.WEB));
   }

   @Test
   void checkout_UserNotFound() {
      when(userService.getUser(userId)).thenReturn(null);
      when(cartService.getCartByUser(user)).thenReturn(cart);

      assertThrows(UserNotFoundException.class, () -> checkoutService.checkout(userId, PaymentOptions.WEB));
   }


   @Test
   void processWebPayment() {
      when(cartService.getCartByUser(user)).thenReturn(cart);
      when(cartService.saveCart(cart)).thenReturn(cart);

      checkoutService.processWebPayment(cart);

      assertEquals(PaymentStatus.SUCCESS, cart.getPaymentStatus());
      assertFalse(cart.isActive());
      verify(cartService, times(1)).saveCart(cart);
   }

   @Test
   void processUSSDPayment() {
      when(cartService.getCartByUser(user)).thenReturn(cart);
      when(cartService.saveCart(cart)).thenReturn(cart);

      checkoutService.processUSSDPayment(cart);

      assertEquals(PaymentStatus.SUCCESS, cart.getPaymentStatus());
      assertFalse(cart.isActive());
      verify(cartService, times(1)).saveCart(cart);
   }

   @Test
   void processTransferPayment() {
      when(cartService.getCartByUser(user)).thenReturn(cart);
      when(cartService.saveCart(cart)).thenReturn(cart);

      checkoutService.processTransferPayment(cart);

      assertEquals(PaymentStatus.SUCCESS, cart.getPaymentStatus());
      assertFalse(cart.isActive());
      verify(cartService, times(1)).saveCart(cart);
   }
}