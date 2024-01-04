package com.interswitch.Bookstore.service.Controllers;

import com.interswitch.Bookstore.service.Models.Book;
import com.interswitch.Bookstore.service.Services.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {

   private final BookService bookService;


   public BookController(BookService bookService) {
      this.bookService = bookService;
   }

   @GetMapping("/books")
   public ResponseEntity<List<Book>> books() {
      return ResponseEntity.ok(bookService.books());
   }


}
