package com.interswitch.Bookstore.service.Repositories;

import com.interswitch.Bookstore.service.Enums.BookGenre;
import com.interswitch.Bookstore.service.Enums.PaymentStatus;
import com.interswitch.Bookstore.service.Models.Book;
import com.interswitch.Bookstore.service.Models.Cart;
import com.interswitch.Bookstore.service.Models.CartBook;
import com.interswitch.Bookstore.service.Models.User;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CartRepositoryTest {

   @Autowired
   private CartRepository cartRepository;

   @Autowired
   private UserRepository userRepository;

   private List<Book> books;

   private Cart cart;

   private User user;

   private List<CartBook> cartBookList;

   Long userId;
   Long cartId;

   @BeforeEach
   void setUp(){
      userId = 1l;
      user  = new User(userId, "John", "Doe", "johndoe@gmail.com","12345" );
      userRepository.save(user);

      books = List.of(
              new Book(1L, "china Town",      BookGenre.FICTION, "789-0-4341-1348-0", "Jackie Chan", "1998", 350.0),
              new Book(2L,"Western Canada",   BookGenre.THRILLER, "789-0-4341-1347-1", "Jack Altman", "1992", 750.0)
      );

      cartBookList = new ArrayList<>();
      cartBookList.add(new CartBook(books.get(0), cart, 1));
      cartBookList.add(new CartBook(books.get(1), cart, 4));

      cartId = 2l;
      cart    = new Cart(cartId, user, cartBookList, 0.00,  PaymentStatus.PENDING, false);

      cartRepository.save(cart);
   }

   @AfterEach
   void tearDown(){
      cart = null;
      cartRepository.deleteAll();
   }



   @Test
   void findByUser_IdTest(){
      List<Cart> foundCarts = cartRepository.findByUser_Id(user.getId());

      assertEquals(1, foundCarts.size());
      assertEquals(cart.getId(), foundCarts.get(0).getId());
      assertEquals(cart.getUser().getEmail(), foundCarts.get(0).getUser().getEmail());
      assertEquals(cart.getTotalAmount(), foundCarts.get(0).getTotalAmount());
   }


   @Test
   @Transactional
   void findByUserIdWithBooksTest() {/*.......*/}





   @Test
   void findByUserTest(){
      cart.setUser(user);
      cartRepository.save(cart);

      Cart foundCart = cartRepository.findByUser(user);

      assertEquals(cart.getUser().getFirstName(), foundCart.getUser().getFirstName());
      assertEquals(cart.getUser().getLastName(),  foundCart.getUser().getLastName());
      assertEquals(cart.getUser().getEmail(),     foundCart.getUser().getEmail());
   }

}
