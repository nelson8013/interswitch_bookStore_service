package com.interswitch.Bookstore.service.Controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.interswitch.Bookstore.service.Dtos.Requests.Checkout.CheckoutRequest;
import com.interswitch.Bookstore.service.Enums.BookGenre;
import com.interswitch.Bookstore.service.Enums.PaymentOptions;
import com.interswitch.Bookstore.service.Enums.PaymentStatus;
import com.interswitch.Bookstore.service.Models.Book;
import com.interswitch.Bookstore.service.Models.Cart;
import com.interswitch.Bookstore.service.Models.CartBook;
import com.interswitch.Bookstore.service.Models.User;
import com.interswitch.Bookstore.service.Services.CartService;
import com.interswitch.Bookstore.service.Services.CheckoutService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CheckoutController.class)
public class CheckoutControllerTest {

   @Autowired
   private MockMvc mockMvc;

   @MockBean
   private CheckoutService checkoutService;

   private List<Book> books;

   private Cart cart;

   private User user;

   private List<CartBook> cartBookList;

   private CheckoutRequest request;


   private Book chinaTown;
   private Book westernCanada;
   private Book theGreatGatsby;

   Long userId;
   Long cartId;
   Long chinaTownId;
   Long westernCanadaId;
   Long theGreatGatsbyId;



   @BeforeEach
   void setUp(){
      userId = 1l;
      user  = new User(userId, "John", "Doe", "johndoe@gmail.com","12345" );

      chinaTown       = new Book(chinaTownId, "china Town",          BookGenre.FICTION, "789-0-4341-1348-0", "Jackie Chan", "1998", 350.0);
      westernCanada   = new Book(westernCanadaId,"Western Canada",   BookGenre.THRILLER, "789-0-4341-1347-1", "Jack Altman", "1992", 750.0);
      theGreatGatsby  = new Book(theGreatGatsbyId,"The Great Gatsby",BookGenre.FICTION, "978-0-7432-7356-5", "F. Scott Fitzgerald", "1925", 300.0);

      books = Arrays.asList(chinaTown, theGreatGatsby, westernCanada);


      cartBookList = new ArrayList<>();
      cartBookList.add(new CartBook(books.get(0), cart, 1));
      cartBookList.add(new CartBook(books.get(1), cart, 4));

      cartId = 2l;
      cart   = new Cart(cartId, user, cartBookList, 0.00,  PaymentStatus.PENDING, false);
      cart.setUser(user);

      request = new CheckoutRequest(userId, PaymentOptions.TRANSFER);

   }

   @AfterEach
   void tearDown(){
   }

   @Test
   void testCheckOut() throws Exception {
      ObjectMapper mapper = new ObjectMapper();
      mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
      ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
      String requestJson = mapper.writeValueAsString(request);

      when(checkoutService.checkout(request.getUserId(), request.getPaymentOption())).thenReturn(cart);
      this.mockMvc.perform(
                      post("/checkout")
                              .contentType( MediaType.APPLICATION_JSON)
                              .content(requestJson))
              .andDo( print())
              .andExpect( status().isCreated());
   }
}
