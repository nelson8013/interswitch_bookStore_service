package com.interswitch.Bookstore.service.Exceptions.BookInventoryExceptions;

public class BookInventoryNotFoundException extends RuntimeException{
   public BookInventoryNotFoundException(String message){
      super(message);
   }
}
