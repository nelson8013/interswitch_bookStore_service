package com.interswitch.Bookstore.service.Controllers;

import com.interswitch.Bookstore.service.Dtos.Requests.CartRequest;
import com.interswitch.Bookstore.service.Dtos.Responses.AddToCartResponse;
import com.interswitch.Bookstore.service.Dtos.Responses.ViewCartResponse;
import com.interswitch.Bookstore.service.Models.Book;
import com.interswitch.Bookstore.service.Models.BookCartItem;
import com.interswitch.Bookstore.service.Models.Cart;
import com.interswitch.Bookstore.service.Services.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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


//   @PostMapping("/cart/add-to-cart")
//   public ResponseEntity<AddToCartResponse> addToCart(@RequestBody CartRequest request){
//      return new ResponseEntity<>(cartService.addToCart(request.getUserId(), request.getBookIds(), request.getQuantities()), HttpStatus.CREATED);
//   }

   @PostMapping("/cart/add-to-cart")
   public void  addToCart(@RequestBody CartRequest request){
      cartService.addToCart(request.getUserId(), request.getBookIds(), request.getQuantities() );
   }

   @GetMapping("/cart/viewCart/{userId}")
   public ResponseEntity<ViewCartResponse> viewCart(@PathVariable("userId") Long userId){
      List<BookCartItem> bookCartItems = cartService.viewCart(userId).getBookItems();
      return ResponseEntity.ok(new ViewCartResponse(bookCartItems));
   }
}
