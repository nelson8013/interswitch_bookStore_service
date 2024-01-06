package com.interswitch.Bookstore.service.Exceptions.PaymentExceptions;

public class PaymentAlreadyMadeException extends RuntimeException {
   public PaymentAlreadyMadeException(String message){
      super(message);
   }
}
