package com.interswitch.Bookstore.service.Dtos.Responses.Cart;

import com.interswitch.Bookstore.service.Models.Book;
import com.interswitch.Bookstore.service.Models.CartBook;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ViewCartResponse {
   private List<CartBook> cartBooks;
   private double totalAmount;
   private boolean status;
   private LocalDateTime date;

   public ViewCartResponse(List<CartBook> cartBooks, double totalAmount, boolean status, LocalDateTime date) {
      this.cartBooks = cartBooks;
      this.totalAmount = totalAmount;
      this.status = status;
      this.date = date;
   }


}