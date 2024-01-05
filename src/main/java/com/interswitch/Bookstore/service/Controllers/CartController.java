package com.interswitch.Bookstore.service.Controllers;

import com.interswitch.Bookstore.service.Dtos.Requests.CartRequest;
import com.interswitch.Bookstore.service.Models.Cart;
import com.interswitch.Bookstore.service.Services.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.List;

@RestController
public class CartController {

   private final CartService cartService;

   public CartController(CartService cartService) {
      this.cartService = cartService;
   }


   @PostMapping("/cart/add-to-cart")
   public ResponseEntity<Cart> addToCart(@RequestBody CartRequest request){
      return new ResponseEntity<>(cartService.addToCart(request.getUserId(), request.getBookIds()), HttpStatus.CREATED);
   }

   @GetMapping("/viewCart/{userId}")
   public ResponseEntity<List<Cart>> viewCart(@PathVariable("userId") Long userId){
      return ResponseEntity.ok(cartService.viewCart(userId));
   }
}
