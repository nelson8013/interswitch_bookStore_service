package com.interswitch.Bookstore.service.Exceptions.BookExceptions;

public class BookNotFoundException extends RuntimeException {

   public BookNotFoundException(String message) {
      super(message);
   }
   public BookNotFoundException(Throwable cause) {
      super(cause);
   }
}
