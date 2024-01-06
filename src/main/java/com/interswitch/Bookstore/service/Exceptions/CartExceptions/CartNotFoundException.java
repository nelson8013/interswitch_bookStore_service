package com.interswitch.Bookstore.service.Exceptions.CartExceptions;

public class CartNotFoundException extends RuntimeException {
   public CartNotFoundException(String message) {
      super(message);
   }
}
