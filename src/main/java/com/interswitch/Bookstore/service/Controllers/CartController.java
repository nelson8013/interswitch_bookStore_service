package com.interswitch.Bookstore.service.Controllers;

import com.interswitch.Bookstore.service.Dtos.Requests.Cart.CartRequest;
import com.interswitch.Bookstore.service.Dtos.Responses.AddToCartResponse;
import com.interswitch.Bookstore.service.Dtos.Responses.ViewCartResponse;
import com.interswitch.Bookstore.service.Services.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * The Cart controller.
 *
 * @author Nelson Ekpenyong
 */
@RestController
public class CartController {

   private final CartService cartService;


   public CartController(CartService cartService) {
      this.cartService = cartService;
   }


   @PostMapping("/cart/add-to-cart")
   public ResponseEntity<AddToCartResponse> addToCart(@RequestBody CartRequest request){
      return new ResponseEntity<>(cartService.addToCart(request), HttpStatus.CREATED);
   }


   @GetMapping("/cart/viewCart/userId") // '/user?user=1'
   public ResponseEntity<ViewCartResponse> viewCart(@RequestParam("userId") Long userId){
      ViewCartResponse cart = cartService.viewCart(userId);
      return ResponseEntity.ok(new ViewCartResponse( cart.getCartBooks(), cart.getTotalAmount(), cart.isStatus(), cart.getDate()));
   }
}