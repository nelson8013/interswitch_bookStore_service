package com.interswitch.Bookstore.service.Controllers;

import com.interswitch.Bookstore.service.Dtos.Responses.PurchaseHistory.PurchaseHistoryResponse;
import com.interswitch.Bookstore.service.Enums.BookGenre;
import com.interswitch.Bookstore.service.Enums.PaymentStatus;
import com.interswitch.Bookstore.service.Models.Book;
import com.interswitch.Bookstore.service.Models.Cart;
import com.interswitch.Bookstore.service.Models.CartBook;
import com.interswitch.Bookstore.service.Models.User;
import com.interswitch.Bookstore.service.Services.CartService;
import com.interswitch.Bookstore.service.Services.PurchaseHistoryService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.is;

@WebMvcTest(PurchaseHistoryController.class)
public class PurchaseHistoryControllerTest {

   @Autowired
   private MockMvc mockMvc;

   @MockBean
   private PurchaseHistoryService purchaseHistoryService;

   private PurchaseHistoryResponse response;

   private List<CartBook> cartBookList;

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



   @BeforeEach
   void setUp(){

      userId = 1l;
      cartId = 1l;
      chinaTownId      = 1l;
      westernCanadaId  = 2l;
      theGreatGatsbyId = 3l;

      user  = new User(userId, "John", "Doe", "johndoe@gmail.com","12345" );


      chinaTown       = new Book(chinaTownId, "china Town",          BookGenre.FICTION, "789-0-4341-1348-0", "Jackie Chan", "1998", 350.0);
      westernCanada   = new Book(westernCanadaId,"Western Canada",   BookGenre.THRILLER, "789-0-4341-1347-1", "Jack Altman", "1992", 750.0);
      theGreatGatsby  = new Book(theGreatGatsbyId,"The Great Gatsby",BookGenre.FICTION, "978-0-7432-7356-5", "F. Scott Fitzgerald", "1925", 300.0);

      books = Arrays.asList(chinaTown, theGreatGatsby, westernCanada);

      cartBookList = new ArrayList<>();
      cartBookList.add(new CartBook(books.get(0), cart, 2));
      cartBookList.add(new CartBook(books.get(1), cart, 1));
      cartBookList.add(new CartBook(books.get(2), cart, 2));

      cart            = new Cart(cartId, user, cartBookList, 0.00,  PaymentStatus.PENDING, false);
      response = new PurchaseHistoryResponse(
              user.getId(),
              books.size(),
              cartBookList.stream()
                      .mapToDouble(cartBook -> cartBook.getBook().getPrice() * cartBook.getQuantity())
                      .sum(),
              LocalDateTime.now(),
              "TRANSFER"
      );
   }

   @AfterEach
   void tearDown() {
   }

   @Test
   void testGetPurchaseHistory() throws Exception {
      when(purchaseHistoryService.getPurchaseHistory(user.getId())).thenReturn(Collections.singletonList(response));
      this.mockMvc.perform(get("/purchase-history/" + user.getId()))
              .andDo(print())
              .andExpect(status().isOk())
              .andExpect(jsonPath("$").isArray())
              .andExpect(jsonPath("$[0].user").value(user.getId()))
              .andExpect(jsonPath("$[0].numberOfBooks").value(Collections.singletonList(response).get(0).getNumberOfBooks()))
              .andExpect(jsonPath("$[0].totalAmount").value(Collections.singletonList(response).get(0).getTotalAmount()))
              .andExpect(jsonPath("$[0].transactionTime").exists())
              .andExpect(jsonPath("$[0].paymentMethod").value(Collections.singletonList(response).get(0).getPaymentMethod()));
   }

}
