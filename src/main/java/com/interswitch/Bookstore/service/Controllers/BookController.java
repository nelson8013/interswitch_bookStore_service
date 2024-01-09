package com.interswitch.Bookstore.service.Controllers;

import com.interswitch.Bookstore.service.Enums.BookGenre;
import com.interswitch.Bookstore.service.Models.Book;
import com.interswitch.Bookstore.service.Services.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

import static com.interswitch.Bookstore.service.Utils.BookHelperMethods.*;


/**
 * The Book controller.
 *
 * @author Nelson Ekpenyong
 *
 * */
@RestController
public class BookController {

   private final BookService bookService;
   private static final Logger LOGGER = LoggerFactory.getLogger(BookService.class);


   public BookController(BookService bookService) {
      this.bookService = bookService;
   }

   @GetMapping("/books")
   public ResponseEntity<List<Book>> getBooks() {
      return ResponseEntity.ok(bookService.books());
   }

   @GetMapping("/books/{id}")
   public ResponseEntity<Book> getBook(@PathVariable("id") Long id) {
     return ResponseEntity.ok(bookService.book(id));
  }

   @GetMapping("/books/title")
   public ResponseEntity<List<Book>> getBookByTitle(@RequestParam String title) {
      return ResponseEntity.ok(bookService.getBookByTitle(title));
   }

   @GetMapping("/books/author")
   public ResponseEntity<List<Book>> getBookByAuthor(@RequestParam String author) {
      return ResponseEntity.ok(bookService.getBookByAuthor(author));
   }

   @GetMapping("/books/year")
   public ResponseEntity<List<Book>> getBookByYear(@RequestParam String year) {
      return ResponseEntity.ok(bookService.getBookByPublicationYear(year));
   }

   @GetMapping("/books/genre")
   public ResponseEntity<List<Book>> getBookByGenre(@RequestParam BookGenre genre) {
      return ResponseEntity.ok(bookService.getBookByGenre(genre));
   }


   @GetMapping("/books/search")
   public List<Book> searchBooks(
           @RequestParam(defaultValue = "") String title,
           @RequestParam(defaultValue = "") String author,
           @RequestParam(defaultValue = "") String yearOfPublication,
           @RequestParam(defaultValue = "") String genre
   ) {
      try {
         if (areAllParamsEmpty(title, author, yearOfPublication, genre)) {
            return bookService.books();
         } else {
            List<Book> titleResult  = title.isEmpty() ? Collections.emptyList() :             bookService.getBookByTitle(title);
            List<Book> authorResult = author.isEmpty() ? Collections.emptyList() :            bookService.getBookByAuthor(author);
            List<Book> yearResult   = yearOfPublication.isEmpty() ? Collections.emptyList() : bookService.getBookByPublicationYear(yearOfPublication);
            List<Book> genreResult  = genre.isEmpty() ? Collections.emptyList() :             bookService.getBookByGenre(BookGenre.valueOf(genre.toUpperCase()));

            List<Book> result = combineResults(titleResult, authorResult, yearResult, genreResult);

            return result;
         }
      } catch (Exception error) {
         LOGGER.error("An error occurred while trying to search books with these parameters: " +
                 "title={}, author={}, yearOfPublication={}, genre={}", title, author, yearOfPublication, genre, error);
         return Collections.emptyList();
      }
   }



}
