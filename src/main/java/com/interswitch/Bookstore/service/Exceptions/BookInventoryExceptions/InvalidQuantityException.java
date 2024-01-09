package com.interswitch.Bookstore.service.Exceptions.BookInventoryExceptions;

public class InvalidQuantityException extends RuntimeException{

   public InvalidQuantityException(String message){
      super(message);
   }
}
