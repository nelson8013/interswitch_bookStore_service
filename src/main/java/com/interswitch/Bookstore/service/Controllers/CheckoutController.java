package com.interswitch.Bookstore.service.Controllers;

import com.interswitch.Bookstore.service.Dtos.Requests.Checkout.CheckoutRequest;
import com.interswitch.Bookstore.service.Models.Cart;
import com.interswitch.Bookstore.service.Services.CheckoutService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;




/**
 * The Check-Out controller.
 *
 * @author Nelson Ekpenyong
 *
 * */
@RestController
public class CheckoutController {

   private final CheckoutService checkoutService;


   public CheckoutController(CheckoutService checkoutService) {
      this.checkoutService = checkoutService;
   }

   @PostMapping("/checkout")
   public ResponseEntity<Cart> checkout(@RequestBody CheckoutRequest request){
      Cart checkoutPayload = checkoutService.checkout(request.getUserId(), request.getPaymentOption());
      return new ResponseEntity<>(checkoutPayload, HttpStatus.CREATED);
   }


}
