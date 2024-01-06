package com.interswitch.Bookstore.service.Dtos.Responses;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PurchaseHistoryResponse {
   private Long user;
   private int numberOfBooks;
   private Double totalAmount;
   private LocalDateTime transactionTime;
   private String paymentMethod;

   public PurchaseHistoryResponse() {}

   public PurchaseHistoryResponse(Long user, int numberOfBooks, Double totalAmount, LocalDateTime transactionTime, String paymentMethod) {
      this.user            = user;
      this.numberOfBooks   = numberOfBooks;
      this.totalAmount     = totalAmount;
      this.transactionTime = transactionTime;
      this.paymentMethod   = paymentMethod;
   }

}
