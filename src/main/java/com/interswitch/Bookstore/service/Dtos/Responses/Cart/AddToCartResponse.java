package com.interswitch.Bookstore.service.Dtos.Responses.Cart;

import com.interswitch.Bookstore.service.Enums.PaymentStatus;
import com.interswitch.Bookstore.service.Models.Book;
import com.interswitch.Bookstore.service.Models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class AddToCartResponse {

   private User user;
   private List<Book> books;
   private Double totalAmount;
   private PaymentStatus paymentStatus;

   public AddToCartResponse(User user, List<Book> books, Double totalAmount, PaymentStatus paymentStatus) {
      this.user          = user;
      this.books         = new ArrayList<>(books);
      this.totalAmount   = totalAmount;
      this.paymentStatus = paymentStatus;
   }

}