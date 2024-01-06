package com.interswitch.Bookstore.service.Models;

import com.interswitch.Bookstore.service.Enums.PaymentOptions;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class PurchaseHistory {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "user_id")
   private User user;

   private int numberOfBooks;

   @Column(name = "total_amount")
   private Double totalAmount;

   private PaymentOptions paymentMethod;

   private LocalDateTime transactionTime;

   public PurchaseHistory(){}

   public PurchaseHistory(User user, int numberOfBooks, Double totalAmount, LocalDateTime transactionTime, PaymentOptions paymentMethod) {
      this.user            = user;
      this.numberOfBooks   = numberOfBooks;
      this.totalAmount     = totalAmount;
      this.transactionTime = transactionTime;
      this.paymentMethod   = paymentMethod;
   }



}
