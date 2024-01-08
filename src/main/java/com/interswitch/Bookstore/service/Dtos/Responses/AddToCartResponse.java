package com.interswitch.Bookstore.service.Dtos.Responses;

import com.interswitch.Bookstore.service.Enums.PaymentStatus;
import com.interswitch.Bookstore.service.Models.BookCartItem;
import com.interswitch.Bookstore.service.Models.User;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class AddToCartResponse {

   private User user;
   private List<BookCartItem> bookItems;
   private Double totalAmount;
   private PaymentStatus paymentStatus;

   public AddToCartResponse(User user, List<BookCartItem> bookItems, Double totalAmount, PaymentStatus paymentStatus) {
      this.user           = user;
      this.bookItems      = new ArrayList<>(bookItems);
      this.totalAmount    = totalAmount;
      this.paymentStatus  = paymentStatus;
   }

}
