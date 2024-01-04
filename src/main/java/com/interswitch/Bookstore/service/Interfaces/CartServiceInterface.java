package com.interswitch.Bookstore.service.Interfaces;


import com.interswitch.Bookstore.service.Models.Cart;

import java.util.List;


public interface CartServiceInterface {
   Cart addToCart(Cart cart);
   List<Cart> viewCart(Long  userId);
}
