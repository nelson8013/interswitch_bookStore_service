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
      initializeBooks();
   }



   /**
    * Initializes the Database with 10 books.
    */
    @Override
    @PostConstruct
    public void initializeBooks() {

      if (bookRepository.count() == 0) {
         Book chinaTown           = new Book("china Town",         BookGenre.THRILLER, "789-0-4341-1347-0", "Jackie Chan", "1998", 350.0);
         Book theGreatGatsby      = new Book("The Great Gatsby",   BookGenre.FICTION, "978-0-7432-7356-5", "F. Scott Fitzgerald", "1925", 300.0);
         Book intoTheWild         = new Book("Into the Wild",      BookGenre.FICTION, "978-0-385-48680-2", "Jon Krakauer", "1996", 300.0);
         Book orientExpressMurder = new Book("Murder on the Orient Express", BookGenre.MYSTERY, "978-0-06-2693662", "Agatha Christie", "1934", 659.0);
         Book theHobbit           = new Book("The Hobbit",         BookGenre.FICTION, "978-0-618-63420-0", "J.R.R. Tolkien", "1937", 522.0);
         Book theRaven            = new Book("The Raven",          BookGenre.POETRY, "978-1-84749-310-3", "Edgar Allan Poe", "1845", 522.0);
         Book it                  = new Book("It",                 BookGenre.HORROR, "978-0451169518", "Stephen King", "1986", 800.0);
         Book prideAndPrejudice   = new Book("Pride and Prejudice",BookGenre.FICTION, "978-1-85326-000-9", "Jane Austen", "1813", 429.0);
         Book catch22             = new Book("Catch-22",           BookGenre.SATIRE, "978-0-684-85358-7", "Joseph Heller", "1961", 429.0);
         Book tattooGirl          = new Book("The Girl with the Dragon Tattoo", BookGenre.THRILLER, "978-0-307-45440-6", "Stieg Larsson", "2005", 750.0);

         bookRepository.saveAll(Arrays.asList(chinaTown, theGreatGatsby, intoTheWild, orientExpressMurder,theHobbit, theRaven, it, prideAndPrejudice, catch22, tattooGirl));

         LOGGER.info("Initialized default books");
      }

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
    List<Book>  getAllBooksById( List<Long> bookIds ){
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
