package com.interswitch.Bookstore.service.Services;

import com.interswitch.Bookstore.service.Interfaces.CartServiceInterface;
import com.interswitch.Bookstore.service.Models.Book;
import com.interswitch.Bookstore.service.Models.Cart;
import com.interswitch.Bookstore.service.Repositories.CartRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService implements CartServiceInterface {

   private final CartRepository cartRepository;

   public CartService(CartRepository cartRepository) {
      this.cartRepository = cartRepository;
   }


   @Override
   public Cart addToCart(Cart cart) {
      return cartRepository.save(cart);
   }

   @Override
   public List<Cart> viewCart(Long  userId) {
      return cartRepository.findByUser_Id(userId);
   }
}
