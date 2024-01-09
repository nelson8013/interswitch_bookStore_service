package com.interswitch.Bookstore.service.Interfaces;


import com.interswitch.Bookstore.service.Dtos.Requests.Cart.CartRequest;
import com.interswitch.Bookstore.service.Dtos.Responses.Cart.AddToCartResponse;
import com.interswitch.Bookstore.service.Dtos.Responses.Cart.ViewCartResponse;
import com.interswitch.Bookstore.service.Models.Cart;
import com.interswitch.Bookstore.service.Models.User;


public interface CartServiceInterface {

   Cart getCartByUser(User user);
   AddToCartResponse addToCart(CartRequest request);
   ViewCartResponse viewCart(Long  userId);
}
