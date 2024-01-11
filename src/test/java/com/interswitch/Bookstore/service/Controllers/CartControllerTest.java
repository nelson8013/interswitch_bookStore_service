package com.interswitch.Bookstore.service.Controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.interswitch.Bookstore.service.Dtos.Requests.Cart.BookQtyDto;
import com.interswitch.Bookstore.service.Dtos.Requests.Cart.CartRequest;
import com.interswitch.Bookstore.service.Dtos.Responses.Cart.AddToCartResponse;
import com.interswitch.Bookstore.service.Dtos.Responses.Cart.ViewCartResponse;
import com.interswitch.Bookstore.service.Enums.BookGenre;
import com.interswitch.Bookstore.service.Enums.PaymentStatus;
import com.interswitch.Bookstore.service.Models.Book;
import com.interswitch.Bookstore.service.Models.Cart;
import com.interswitch.Bookstore.service.Models.CartBook;
import com.interswitch.Bookstore.service.Models.User;
import com.interswitch.Bookstore.service.Services.BookService;
import com.interswitch.Bookstore.service.Services.CartService;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CartController.class)
public class CartControllerTest {
   @Autowired
   private MockMvc mockMvc;

   @MockBean
   private CartService cartService;

   private User user;
   private Cart cart;
   private Book chinaTown;
   private Book westernCanada;
   private Book theGreatGatsby;

   private Long userId;
   private Long cartId;
   private Long chinaTownId;
   private Long westernCanadaId;
   private Long theGreatGatsbyId;

   private List<CartBook> cartBookList;
   private List<Book> books;

   private CartRequest request;

   private AddToCartResponse response;

   private ViewCartResponse viewCartResponse;

   @BeforeEach
   void setUp(){
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

      request = new CartRequest(user.getId(), List.of(
              new BookQtyDto(2L, 1L),
              new BookQtyDto(3L, 1L),
              new BookQtyDto(4L, 3L)
      ));

      response =  new AddToCartResponse(user, books, cart.getTotalAmount(), PaymentStatus.PENDING);

      viewCartResponse = new ViewCartResponse(cart.getCartbooks(), cart.getTotalAmount(), cart.isActive(), cart.getCreatedAt());

   }

   @AfterEach
   void tearDown(){}

   @Test
   void testAddToCart() throws Exception {
      ObjectMapper mapper = new ObjectMapper();
      mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
      ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
      String requestJson = mapper.writeValueAsString(request);

      when(cartService.addToCart(request)).thenReturn(response);
      this.mockMvc.perform(
                      post("/cart/add-to-cart")
                              .contentType( MediaType.APPLICATION_JSON)
                              .content(requestJson))
              .andDo( print())
              .andExpect( status().isCreated());
   }

   @Test
   void testViewCart() throws Exception {
      when(cartService.viewCart(user.getId())).thenReturn(viewCartResponse);
      this.mockMvc.perform( get("/cart/viewCart/userId?userId="+ user.getId()) ).andDo( print() ).andExpect( status().isOk());

   }
}
