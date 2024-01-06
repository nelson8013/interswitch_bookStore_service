package com.interswitch.Bookstore.service.Interfaces;

import com.interswitch.Bookstore.service.Enums.PaymentOptions;
import com.interswitch.Bookstore.service.Models.Cart;
import com.interswitch.Bookstore.service.Repositories.CartRepository;

public interface CheckoutServiceInterface {
   Cart  checkout(Long userId, PaymentOptions paymentOption);

   void processWebPayment(Cart cart);
   void processUSSDPayment(Cart cart);
   void processTransferPayment(Cart cart);
}
