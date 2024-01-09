package com.interswitch.Bookstore.service.Repositories;

import com.interswitch.Bookstore.service.Enums.BookGenre;
import com.interswitch.Bookstore.service.Models.Book;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookRepositoryTest {

   @Autowired
   private BookRepository bookRepository;

   private BookGenre genre;
   Book book;

   @BeforeEach
   void setUp(){
      book = new Book("china Town", genre.THRILLER, "789-0-4341-1347-0", "Jackie Chan", "1998", 350.0);
      bookRepository.save(book);
   }

   @AfterEach
   void tearDown(){
      book = null;
      bookRepository.deleteAll();
   }

   @Test
   void findByTitleTest(){
      List<Book> bookList =  bookRepository.findByTitle("china Town");

      assertThat( bookList.get(0).getTitle()).isEqualTo( book.getTitle());
      assertThat( bookList.get(0).getAuthor()).isEqualTo(book.getAuthor());
      assertThat( bookList.get(0).getGenre()).isEqualTo( book.getGenre());
      assertThat( bookList.get(0).getPrice()).isEqualTo( book.getPrice());
      assertThat( bookList.get(0).getIsbn()).isEqualTo(  book.getIsbn());

   }
   @Test
   void findByTitleTest_NotFound() {
      List<Book> bookList = bookRepository.findByTitle("Surulere");
      assertThat(bookList.isEmpty()).isTrue();
   }



   @Test
   void findByAuthorTest(){
      List<Book> bookList =  bookRepository.findByAuthor("Jackie Chan");

      assertThat( bookList.get(0).getTitle()).isEqualTo( book.getTitle());
      assertThat( bookList.get(0).getAuthor()).isEqualTo(book.getAuthor());
      assertThat( bookList.get(0).getGenre()).isEqualTo( book.getGenre());
      assertThat( bookList.get(0).getPrice()).isEqualTo( book.getPrice());
      assertThat( bookList.get(0).getIsbn()).isEqualTo(  book.getIsbn());

   }
   @Test
   void findByAuthorTest_NotFound() {
      List<Book> bookList = bookRepository.findByAuthor("Micheal Jackson");
      assertThat(bookList.isEmpty()).isTrue();
   }



   @Test
   void findByYearOfPublicationTest(){
      List<Book> bookList =  bookRepository.findByYearOfPublication("1998");

      assertThat( bookList.get(0).getTitle()).isEqualTo( book.getTitle());
      assertThat( bookList.get(0).getAuthor()).isEqualTo(book.getAuthor());
      assertThat( bookList.get(0).getGenre()).isEqualTo( book.getGenre());
      assertThat( bookList.get(0).getPrice()).isEqualTo( book.getPrice());
      assertThat( bookList.get(0).getIsbn()).isEqualTo(  book.getIsbn());
   }
   @Test
   void findByYearOfPublicationTest_NotFound() {
      List<Book> bookList = bookRepository.findByYearOfPublication("2024");
      assertThat(bookList.isEmpty()).isTrue();
   }


   @Test
   void findByGenreTest(){
      List<Book> bookList =  bookRepository.findByGenre(genre.THRILLER);

      assertThat( bookList.get(0).getTitle()).isEqualTo( book.getTitle());
      assertThat( bookList.get(0).getAuthor()).isEqualTo(book.getAuthor());
      assertThat( bookList.get(0).getGenre()).isEqualTo( book.getGenre());
      assertThat( bookList.get(0).getPrice()).isEqualTo( book.getPrice());
      assertThat( bookList.get(0).getIsbn()).isEqualTo(  book.getIsbn());

   }


   }
