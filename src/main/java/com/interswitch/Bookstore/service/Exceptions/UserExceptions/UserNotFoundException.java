package com.interswitch.Bookstore.service.Exceptions.UserExceptions;

public class UserNotFoundException extends RuntimeException {
   public UserNotFoundException(String message) {
      super(message);
   }
}
