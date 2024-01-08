package com.interswitch.Bookstore.service.Interfaces;


import com.interswitch.Bookstore.service.Dtos.Responses.AddToCartResponse;
import com.interswitch.Bookstore.service.Dtos.Responses.ViewCartResponse;
import com.interswitch.Bookstore.service.Models.Cart;
import com.interswitch.Bookstore.service.Models.User;

import java.util.List;


public interface CartServiceInterface {

   Cart getCartByUser(User user);
//   AddToCartResponse addToCart(Long userId, List<Long> bookIds, List<Integer> quantities);
   void addToCart(Long userId, List<Long> bookIds, List<Integer> quantities);
   ViewCartResponse viewCart(Long  userId);
}
