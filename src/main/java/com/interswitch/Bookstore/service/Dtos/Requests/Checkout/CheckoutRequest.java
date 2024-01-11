package com.interswitch.Bookstore.service.Dtos.Requests.Checkout;

import com.interswitch.Bookstore.service.Enums.PaymentOptions;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CheckoutRequest {
   private Long userId;
   private PaymentOptions paymentOption;

   public CheckoutRequest(){}
   public CheckoutRequest(Long userId, PaymentOptions paymentOption) {
      this.userId = userId;
      this.paymentOption = paymentOption;
   }
}
