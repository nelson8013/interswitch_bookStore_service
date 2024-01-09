package com.interswitch.Bookstore.service.Services;

import com.interswitch.Bookstore.service.Controllers.BookController;
import com.interswitch.Bookstore.service.Enums.BookGenre;
import com.interswitch.Bookstore.service.Interfaces.BookServiceInterface;
import com.interswitch.Bookstore.service.Models.Book;
import com.interswitch.Bookstore.service.Repositories.BookRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class BookServiceTest {

   @Mock
   private BookRepository bookRepository;

   @Mock
   private BookService bookservice;

   @InjectMocks
   private BookController bookController;

   private BookServiceInterface bookServiceInterface;

   private AutoCloseable autoCloseable;

   private BookGenre genre;

   Book book;
   Long bookId;

   @BeforeEach
   void setUp() {
      autoCloseable = MockitoAnnotations.openMocks(this);
      bookId = 1l;
      bookServiceInterface = new BookService(bookRepository);
      bookController       = new BookController(bookservice);

      book = new Book("china Town", genre.THRILLER, "789-0-4341-1347-0", "Jackie Chan", "1998", 350.0);

   }

   @AfterEach
   void tearDown() throws Exception {
      autoCloseable.close();
   }

   @Test
   void book() {
      mock(Book.class);
      mock(BookRepository.class);

      when( bookRepository.findById(bookId)).thenReturn(Optional.ofNullable(book));

      assertThat(bookServiceInterface.book(bookId).getTitle()).isEqualTo(book.getTitle());
      assertThat(bookServiceInterface.book(bookId).getAuthor()).isEqualTo(book.getAuthor());
      assertThat(bookServiceInterface.book(bookId).getGenre()).isEqualTo(book.getGenre());
      assertThat(bookServiceInterface.book(bookId).getPrice()).isEqualTo(book.getPrice());
      assertThat(bookServiceInterface.book(bookId).getIsbn()).isEqualTo(book.getIsbn());
   }


   @Test
   void books() {
      mock(Book.class);
      mock(BookRepository.class);

      when( bookRepository.findAll()).thenReturn( new ArrayList<Book>(Collections.singleton(book)));
      assertThat( bookServiceInterface.books().get(0).getTitle() ).isEqualTo(book.getTitle());
      assertThat(bookServiceInterface.books().get(0).getAuthor()).isEqualTo(book.getAuthor());
      assertThat(bookServiceInterface.books().get(0).getGenre()).isEqualTo(book.getGenre());
      assertThat(bookServiceInterface.books().get(0).getPrice()).isEqualTo(book.getPrice());
      assertThat(bookServiceInterface.books().get(0).getIsbn()).isEqualTo(book.getIsbn());

   }


   @Test
   void getAllBooksById() {
      mock(Book.class);
      mock(BookRepository.class);

      when(bookRepository.findAllById(Collections.singletonList(bookId))).thenReturn(Collections.singletonList(book));

      assertThat(bookServiceInterface.getAllBooksById(Collections.singletonList(bookId)).get(0).getTitle()).isEqualTo(book.getTitle());
      assertThat(bookServiceInterface.getAllBooksById(Collections.singletonList(bookId)).get(0).getAuthor()).isEqualTo(book.getAuthor());
      assertThat(bookServiceInterface.getAllBooksById(Collections.singletonList(bookId)).get(0).getGenre()).isEqualTo(book.getGenre());
      assertThat(bookServiceInterface.getAllBooksById(Collections.singletonList(bookId)).get(0).getPrice()).isEqualTo(book.getPrice());
      assertThat(bookServiceInterface.getAllBooksById(Collections.singletonList(bookId)).get(0).getIsbn()).isEqualTo(book.getIsbn());
   }

   @Test
   void getBookByTitle() {
      mock(Book.class);
      mock(BookRepository.class);

      when(bookRepository.findByTitle(book.getTitle())).thenReturn(Collections.singletonList(book));

      assertThat(bookServiceInterface.getBookByTitle(book.getTitle()).get(0).getTitle()).isEqualTo(book.getTitle());
      assertThat(bookServiceInterface.getBookByTitle(book.getTitle()).get(0).getAuthor()).isEqualTo(book.getAuthor());
      assertThat(bookServiceInterface.getBookByTitle(book.getTitle()).get(0).getGenre()).isEqualTo(book.getGenre());
      assertThat(bookServiceInterface.getBookByTitle(book.getTitle()).get(0).getPrice()).isEqualTo(book.getPrice());
      assertThat(bookServiceInterface.getBookByTitle(book.getTitle()).get(0).getIsbn()).isEqualTo(book.getIsbn());
   }


   @Test
   void getBookByAuthor() {
      mock(Book.class);
      mock(BookRepository.class);

      when(bookRepository.findByAuthor(book.getAuthor())).thenReturn(Collections.singletonList(book));

      assertThat(bookServiceInterface.getBookByAuthor(book.getAuthor()).get(0).getTitle()).isEqualTo(book.getTitle());
      assertThat(bookServiceInterface.getBookByAuthor(book.getAuthor()).get(0).getAuthor()).isEqualTo(book.getAuthor());
      assertThat(bookServiceInterface.getBookByAuthor(book.getAuthor()).get(0).getGenre()).isEqualTo(book.getGenre());
      assertThat(bookServiceInterface.getBookByAuthor(book.getAuthor()).get(0).getPrice()).isEqualTo(book.getPrice());
      assertThat(bookServiceInterface.getBookByAuthor(book.getAuthor()).get(0).getIsbn()).isEqualTo(book.getIsbn());
   }

   @Test
   void getBookByPublicationYear() {
      mock(Book.class);
      mock(BookRepository.class);

      when(bookRepository.findByYearOfPublication(book.getYearOfPublication())).thenReturn(Collections.singletonList(book));

      assertThat(bookServiceInterface.getBookByPublicationYear(book.getYearOfPublication()).get(0).getTitle()).isEqualTo(book.getTitle());
      assertThat(bookServiceInterface.getBookByPublicationYear(book.getYearOfPublication()).get(0).getAuthor()).isEqualTo(book.getAuthor());
      assertThat(bookServiceInterface.getBookByPublicationYear(book.getYearOfPublication()).get(0).getGenre()).isEqualTo(book.getGenre());
      assertThat(bookServiceInterface.getBookByPublicationYear(book.getYearOfPublication()).get(0).getPrice()).isEqualTo(book.getPrice());
      assertThat(bookServiceInterface.getBookByPublicationYear(book.getYearOfPublication()).get(0).getIsbn()).isEqualTo(book.getIsbn());
   }

   @Test
   void getBookByGenre() {
      mock(Book.class);
      mock(BookRepository.class);

      when(bookRepository.findByGenre(book.getGenre())).thenReturn(Collections.singletonList(book));

      assertThat(bookServiceInterface.getBookByGenre(book.getGenre()).get(0).getTitle()).isEqualTo(book.getTitle());
      assertThat(bookServiceInterface.getBookByGenre(book.getGenre()).get(0).getAuthor()).isEqualTo(book.getAuthor());
      assertThat(bookServiceInterface.getBookByGenre(book.getGenre()).get(0).getGenre()).isEqualTo(book.getGenre());
      assertThat(bookServiceInterface.getBookByGenre(book.getGenre()).get(0).getPrice()).isEqualTo(book.getPrice());
      assertThat(bookServiceInterface.getBookByGenre(book.getGenre()).get(0).getIsbn()).isEqualTo(book.getIsbn());
   }
}