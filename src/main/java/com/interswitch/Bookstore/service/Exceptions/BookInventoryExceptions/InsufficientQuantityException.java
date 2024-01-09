package com.interswitch.Bookstore.service.Exceptions.BookInventoryExceptions;

public class InsufficientQuantityException extends RuntimeException {
   private Long bookId;
   private int  quantity;
   private int availableQuantity;


   public InsufficientQuantityException(String message){
      super(message);
   }

   public InsufficientQuantityException(Long bookId, int quantity, int availableQuantity, String message){
      super(message);
      this.bookId = bookId;
      this.quantity = quantity;
      this.availableQuantity = availableQuantity;
   }
}
