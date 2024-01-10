package com.interswitch.Bookstore.service.Services;

import com.interswitch.Bookstore.service.Controllers.BookController;
import com.interswitch.Bookstore.service.Controllers.CartController;
import com.interswitch.Bookstore.service.Interfaces.BookServiceInterface;
import com.interswitch.Bookstore.service.Interfaces.CartServiceInterface;
import com.interswitch.Bookstore.service.Models.Book;
import com.interswitch.Bookstore.service.Models.Cart;
import com.interswitch.Bookstore.service.Models.User;
import com.interswitch.Bookstore.service.Repositories.BookRepository;
import com.interswitch.Bookstore.service.Repositories.CartBookRepository;
import com.interswitch.Bookstore.service.Repositories.CartRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class CartServiceTest {

   @Mock
   private CartRepository cartRepository;

   @Mock
   private CartBookRepository cartBookRepository;

   @Mock
   private CartService cartService;

   @Mock
   private UserService userService;

   @Mock
   private BookService bookService;

   @Mock
   private BookInventoryService bookInventoryService;

   @InjectMocks
   private CartController cartController;

   private CartServiceInterface cartServiceInterface;

   private AutoCloseable autoCloseable;

   Cart cart;
   Book book;
   User user;



   @BeforeEach
   void setUp() {
      autoCloseable = MockitoAnnotations.openMocks(this);

   }

   @AfterEach
   void tearDown() throws Exception {
      autoCloseable.close();
   }

   @Test
   void getCartByUser() {
   }

   @Test
   void saveCart() {
   }

   @Test
   void addToCart() {
   }

   @Test
   void viewCart() {
   }
}