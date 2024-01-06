package com.interswitch.Bookstore.service.Exceptions.PaymentExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PaymentExceptionHandler {

   @ExceptionHandler( value = {IllegalPaymentOptionException.class})
   public ResponseEntity<Object> handleIllegalPaymentOptionException(IllegalPaymentOptionException illegalPaymentOptionException){
      PaymentException exception = new PaymentException(illegalPaymentOptionException.getMessage(), illegalPaymentOptionException.getCause(), HttpStatus.NOT_ACCEPTABLE);
      return new ResponseEntity<>(exception, HttpStatus.NOT_ACCEPTABLE);
   }

   @ExceptionHandler( value = {PaymentAlreadyMadeException.class})
   public ResponseEntity<Object> handlePaymentAlreadyMadeException( PaymentAlreadyMadeException paymentAlreadyMadeException){
      PaymentException exception = new PaymentException(paymentAlreadyMadeException.getMessage(), paymentAlreadyMadeException.getCause(), HttpStatus.NOT_ACCEPTABLE);
      return new ResponseEntity<>(exception, HttpStatus.NOT_ACCEPTABLE);
   }
}
