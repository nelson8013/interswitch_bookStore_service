package com.interswitch.Bookstore.service.Exceptions.PaymentExceptions;

public class IllegalPaymentOptionException extends RuntimeException {

   public IllegalPaymentOptionException(String message){
      super(message);
   }

   public IllegalPaymentOptionException(Throwable cause) {
      super(cause);
   }

}
