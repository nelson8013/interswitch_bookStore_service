package com.interswitch.Bookstore.service.Repositories;

import com.interswitch.Bookstore.service.Enums.BookGenre;
import com.interswitch.Bookstore.service.Enums.PaymentStatus;
import com.interswitch.Bookstore.service.Models.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class PurchaseHistoryRepositoryTest {

   @Autowired
   private PurchaseHistoryRepository purchaseHistoryRepository;

   @Autowired
   private UserRepository userRepository;

   @Autowired
   private CartRepository cartRepository;
   @Autowired
   private BookRepository bookRepository;

   @Autowired
   private CartBookRepository cartBookRepository;

   private PurchaseHistory purchaseHistory;

   private User user;
   private Cart cart;
   private Book book;

   private CartBook cartBook;
   List<CartBook> cartBooks;
   Long userId;

   @BeforeEach
   void setUp() {
      userId = 1l;
      user = new User(userId, "John", "Doe", "johndoe@gmail.com", "12345");
      userRepository.save(user);

      Cart cart = new Cart();
      cart.setUser(user);
      cart.setPaymentStatus(PaymentStatus.SUCCESS); // Ensure this matches your query condition
      cartRepository.save(cart);

      book = new Book("Sample Book", BookGenre.FICTION, "ISBN123", "Author", "2022", 19.99);
      bookRepository.save(book);

      CartBook cartBook = new CartBook(book, cart, 2);
      cartBookRepository.save(cartBook);

      CartBook cartBook1 = new CartBook(book, cart, 2);
      CartBook cartBook2 = new CartBook(book, cart, 2);

      cartBooks = Arrays.asList(
              cartBook1, cartBook2
      );
      cartBookRepository.saveAll(cartBooks);
   }

   @AfterEach
   void tearDown(){
      purchaseHistory = null;
      purchaseHistoryRepository.deleteAll();
   }


}
