package com.interswitch.Bookstore.service.Services;

import com.interswitch.Bookstore.service.Enums.BookGenre;
import com.interswitch.Bookstore.service.Exceptions.BookExceptions.BookNotFoundException;
import com.interswitch.Bookstore.service.Interfaces.BookServiceInterface;
import com.interswitch.Bookstore.service.Models.Book;
import com.interswitch.Bookstore.service.Repositories.BookRepository;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;


/**
 * The Book service.
 *
 * @author Nelson Ekpenyong
 */
@Service
public class BookService implements BookServiceInterface {

    private final BookRepository bookRepository;
   private static final Logger LOGGER = LoggerFactory.getLogger(BookService.class);


   /**
    * Instantiates a new Book service with the BookRepository and the book's initializer.
    *
    * @param bookRepository the book repository
    */
    public BookService(BookRepository bookRepository) {
      this.bookRepository = bookRepository;
    }


   /**
    * Get the list of all books.
    * @return The list of books.
    */
    @Override
    public List<Book> books() {
      try {

         return bookRepository.findAll();

      }catch (Exception error) {
         LOGGER.error("An error occurred. Could not fetch books.", error);
         return Collections.emptyList();
      }
   }



   /**
    * Get a books by the book's id.
    *
    * @param id The book id.
    * @return The book.
    */
    @Override
    public Book book(Long id) {
      try {

         return bookRepository.findById(id)
                 .orElseThrow( ()-> new BookNotFoundException("The book with id: " + id + " does not exist"));

      }catch (BookNotFoundException exception) {
         LOGGER.error("Book not found: {}", exception.getMessage());
         throw exception;  //TODO: Nelson, do you really need to rethrow the exception to propagate it?
      }
   }


   /**
    * Get a list of books by the book's ids.
    *
    * @param bookIds The book ids.
    * @return The list of books.
    */
    List<Book>  getAllBooksById( Collection<Long> bookIds ){
      return bookRepository.findAllById(bookIds);
   }


   /**
    * Get a list of books by the book's title.
    *
    * @param title The book title.
    * @return The list of books.
    */
    @Override
    public List<Book> getBookByTitle(String title) {
      try {

         List<Book> books = bookRepository.findByTitle(title);

         if (books.isEmpty()) {
            throw new BookNotFoundException("Book with title: " + title + "' does not exist");
         }

         return books;

      }catch (BookNotFoundException exception) {
         LOGGER.error("Book not found: {}", exception.getMessage());
         throw exception;
      }
   }


   /**
    * Get a list of books by the book's author.
    *
    * @param author The book author.
    * @return The list of books.
    */
    @Override
    public List<Book> getBookByAuthor(String author) {
      try {
         List<Book> books = bookRepository.findByAuthor(author);

         if (books.isEmpty()) {
            throw new BookNotFoundException("Book with author: " + author + " does not exist");
         }

         return books;
      }catch (BookNotFoundException exception) {
         LOGGER.error("Book not found: {}", exception.getMessage());
         throw exception;
      }
   }


   /**
    * Get a list of books by the book's year of publication.
    *
    * @param year The book's year of publication.
    * @return The list of books.
    */
    @Override
    public List<Book> getBookByPublicationYear(String year) {
      try {
         List<Book> books = bookRepository.findByYearOfPublication(year);

         if (books.isEmpty()) {
            throw new BookNotFoundException("No books found for the year: " + year);
         }

         return books;
      }catch (BookNotFoundException exception) {
         LOGGER.error("Book not found: {}", exception.getMessage());
         throw exception;
      }
   }


   /**
    * Get a list of books by the book's genre.
    *
    * @param genre The book's genre.
    * @return The list of books.
    */
    @Override
    public List<Book> getBookByGenre(BookGenre genre) {
      try{

         return bookRepository.findByGenre(genre);

      }catch (Exception error) {
         //Todo: throw error messages in pidgin
         LOGGER.error("An error occurred. That genre may not even exist.", error);
         return Collections.emptyList();
      }
   }


}
