package com.interswitch.Bookstore.service.Exceptions.BookExceptions;

import org.springframework.http.HttpStatus;

public class BookException {

      private final String message;
      private final Throwable cause;
      private final HttpStatus status;

      BookException(String message, Throwable cause, HttpStatus status ){
         this.message = message;
         this.cause   = cause;
         this.status  = status;
      }

      public String getMessage() {
         return this.message;
      }


      public Throwable getCause() {
         return this.cause;
      }


      public HttpStatus getStatus() {
         return this.status;
      }
}
