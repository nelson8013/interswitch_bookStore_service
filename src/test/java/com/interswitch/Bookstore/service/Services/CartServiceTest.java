package com.interswitch.Bookstore.service.Services;

import com.interswitch.Bookstore.service.Dtos.Requests.Cart.BookQtyDto;
import com.interswitch.Bookstore.service.Dtos.Requests.Cart.CartRequest;
import com.interswitch.Bookstore.service.Dtos.Responses.Cart.AddToCartResponse;
import com.interswitch.Bookstore.service.Dtos.Responses.Cart.ViewCartResponse;
import com.interswitch.Bookstore.service.Enums.BookGenre;
import com.interswitch.Bookstore.service.Enums.PaymentStatus;
import com.interswitch.Bookstore.service.Exceptions.BookExceptions.BookNotFoundException;
import com.interswitch.Bookstore.service.Exceptions.CartExceptions.CartNotFoundException;
import com.interswitch.Bookstore.service.Exceptions.UserExceptions.UserNotFoundException;
import com.interswitch.Bookstore.service.Models.*;
import com.interswitch.Bookstore.service.Repositories.CartBookRepository;
import com.interswitch.Bookstore.service.Repositories.CartRepository;
import com.interswitch.Bookstore.service.Repositories.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import static org.assertj.core.api.Assertions.assertThat;

class CartServiceTest {

   @Mock
   private CartRepository cartRepository;

   @Mock
   private CartBookRepository cartBookRepository;

   @Mock
   private UserService userService;

   @Mock
   private BookService bookService;

   @Mock
   private BookInventoryService bookInventoryService;

   @InjectMocks
   private CartService cartService;

   private BookQtyDto bookQtyDto;
   private CartRequest request;
   private ViewCartResponse response;

   private AutoCloseable autoCloseable;


   private User user;
   private Cart cart;
   private Book chinaTown;
   private Book westernCanada;
   private Book theGreatGatsby;

   Long userId;
   Long cartId;
   Long chinaTownId;
   Long westernCanadaId;
   Long theGreatGatsbyId;


   private List<Book> books;
   private List<CartBook> cartBookList;

   @BeforeEach
   void setUp() {
      autoCloseable = MockitoAnnotations.openMocks(this);
      userId = 1l;
      cartId = 1l;
      chinaTownId      = 1l;
      westernCanadaId  = 2l;
      theGreatGatsbyId = 3l;

      user  = new User(userId, "John", "Doe", "johndoe@gmail.com","12345" );

      cartBookList    = new ArrayList<>();


      cart            = new Cart(cartId, user, cartBookList, 0.00,  PaymentStatus.PENDING, false);

      chinaTown       = new Book(chinaTownId, "china Town",          BookGenre.FICTION, "789-0-4341-1348-0", "Jackie Chan", "1998", 350.0);
      westernCanada   = new Book(westernCanadaId,"Western Canada",   BookGenre.THRILLER, "789-0-4341-1347-1", "Jack Altman", "1992", 750.0);
      theGreatGatsby  = new Book(theGreatGatsbyId,"The Great Gatsby",BookGenre.FICTION, "978-0-7432-7356-5", "F. Scott Fitzgerald", "1925", 300.0);

      books = Arrays.asList(chinaTown, theGreatGatsby, westernCanada);

   }

   @AfterEach
   void tearDown() throws Exception {
      try {
         autoCloseable.close();
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   @Test
   void saveCart() {
      when(cartRepository.save(cart)).thenReturn(cart);
      Cart savedCart = cartService.saveCart(cart);
      assertThat(savedCart).isEqualTo(cart);
   }

   @Test
   void addToCart() {

      request = new CartRequest(user.getId(), List.of(
              new BookQtyDto(2L, 1L),
              new BookQtyDto(3L, 1L),
              new BookQtyDto(4L, 3L)
      ));

      when(userService.getUser(user.getId())).thenReturn(user);
      when(bookService.getAllBooksById(anySet())).thenReturn(books);
      when(bookInventoryService.doesBookExist(anyLong())).thenReturn(true);
      when(bookInventoryService.getBookQuantity(anyLong())).thenReturn(40);


      AddToCartResponse response = cartService.addToCart(request);

      assertEquals(user, response.getUser());
      assertEquals(books, response.getBooks());
      assertEquals(1050.0, response.getTotalAmount());
      assertEquals(PaymentStatus.PENDING, response.getPaymentStatus());

      verify(cartRepository).save(any(Cart.class));
      verify(cartBookRepository).saveAll(anyList());
   }

   @Test
   void addToCart_userNotFound() {
      request = new CartRequest(50L, List.of(
              new BookQtyDto(2L, 1L),
              new BookQtyDto(3L, 1L),
              new BookQtyDto(4L, 3L)
      ));

      when(userService.getUser(50L)).thenThrow(UserNotFoundException.class);

      assertThrows(UserNotFoundException.class, () -> cartService.addToCart(request));
   }

   @Test
   void addToCart_booksNotFound() {

      request = new CartRequest(userId, List.of(
              new BookQtyDto(2L, 1L),
              new BookQtyDto(50L, 1L)
      ));

      when(userService.getUser(userId)).thenReturn(user);
      when(bookService.getAllBooksById(anySet())).thenReturn(Collections.emptyList());

      assertThrows(BookNotFoundException.class, () -> cartService.addToCart(request));
   }


   @Test
   void addToCart_mismatchedBookIdsAndQuantities() {
      request = new CartRequest(userId, List.of(
              new BookQtyDto(2L, 1L),
              new BookQtyDto(3L, 2L)
      ));

      when(userService.getUser(userId)).thenReturn(user);
      when(bookService.getAllBooksById(anySet())).thenReturn(books);

      assertThrows(IllegalArgumentException.class, () -> cartService.addToCart(request));
   }


   @Test
   void viewCart_successfulRetrieval() {

      cart.setCartbooks(List.of(
              new CartBook(books.get(0), cart, 2),
              new CartBook(books.get(1), cart, 1),
              new CartBook(books.get(2), cart, 3)
      ));
      cart.setTotalAmount(30.0);
      cart.setActive(true);
      cart.setCreatedAt(LocalDateTime.now());

      when(cartRepository.findByUserIdWithBooks(userId)).thenReturn(Optional.of(cart));

      response = cartService.viewCart(userId);

      List<Book> cartBooks = response.getCartBooks().stream().map(CartBook::getBook).collect(Collectors.toList());

      assertEquals(books, cartBooks);
      assertEquals(30.0, response.getTotalAmount());
      assertTrue(response.isStatus());
   }

   @Test
   void viewCart_cartNotFound() {
      when(cartRepository.findByUserIdWithBooks(userId)).thenReturn(Optional.empty());
      assertThrows(CartNotFoundException.class, () -> cartService.viewCart(userId));
   }

}