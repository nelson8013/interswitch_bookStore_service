package com.interswitch.Bookstore.service.Exceptions.BookExceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BookExceptionHandler {
   @ExceptionHandler( value = {BookNotFoundException.class})
   public ResponseEntity<Object> handleBookNotFoundException(BookNotFoundException bookNotFoundException){
      BookException exception = new BookException(bookNotFoundException.getMessage(), bookNotFoundException.getCause() ,HttpStatus.NOT_FOUND);
      return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
   }

}
