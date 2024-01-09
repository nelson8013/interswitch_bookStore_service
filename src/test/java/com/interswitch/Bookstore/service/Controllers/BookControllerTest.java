package com.interswitch.Bookstore.service.Controllers;

import com.interswitch.Bookstore.service.Enums.BookGenre;
import com.interswitch.Bookstore.service.Models.Book;
import com.interswitch.Bookstore.service.Services.BookService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;


@WebMvcTest(BookController.class)
public class BookControllerTest {
   @Autowired
   private MockMvc mockMvc;

   @MockBean
   private BookService bookService;

   private BookGenre genre;

   Long bookId;

   Book chinaTown;
   Book theGreatGatsby;
   Book intoTheWild;
   Book orientExpressMurder;
   List<Book> mockBooks;
   List<Book> bookList = new ArrayList<Book>();


   @BeforeEach
   void setUp(){
      bookId = 1L;
      chinaTown           = new Book("china Town",         genre.THRILLER, "789-0-4341-1347-0", "Jackie Chan", "1998", 350.0);
      theGreatGatsby      = new Book("The Great Gatsby",   genre.FICTION, "978-0-7432-7356-5", "F. Scott Fitzgerald", "1925", 300.0);
      intoTheWild         = new Book("Into the Wild",      genre.FICTION, "978-0-385-48680-2", "Jon Krakauer", "1996", 300.0);
      orientExpressMurder = new Book("Murder on the Orient Express", genre.MYSTERY, "978-0-06-2693662", "Agatha Christie", "1934", 659.0);


      bookList.add(chinaTown);
      bookList.add(theGreatGatsby);
      bookList.add(intoTheWild);
      bookList.add(orientExpressMurder);

      mockBooks = Collections.singletonList(
              new Book("china Town", BookGenre.THRILLER, "789-0-4341-1347-0", "Jackie Chan", "1998", 350.0)
      );
   }

   @AfterEach
   void tearDown(){}


   @Test
   void testGetBooks() throws Exception {
      when(bookService.books() ).thenReturn(bookList);
      this.mockMvc.perform( get("/books") ).andDo( print()).andExpect(status().isOk());
   }

   @Test
   void testGetBook() throws Exception {
      when(bookService.book(bookId) ).thenReturn(chinaTown);
      this.mockMvc.perform( get("/books/"+ bookId) ).andDo( print()).andExpect(status().isOk());
   }

   @Test
   void testGetBookByTitle() throws Exception {
      when(bookService.getBookByTitle(chinaTown.getTitle()) ).thenReturn(mockBooks);
      this.mockMvc.perform( get("/books/title?title=china%20Town") ).andDo( print()).andExpect(status().isOk());
   }

   @Test
   void testGetBookByAuthor() throws Exception {
      when(bookService.getBookByAuthor(chinaTown.getAuthor())).thenReturn(mockBooks);
      this.mockMvc.perform( get("/books/author?author=Jackie%20Chan") ).andDo( print()).andExpect( status().isOk());
   }

   @Test
   void testGetBookByYear() throws Exception {
      when(bookService.getBookByPublicationYear(chinaTown.getYearOfPublication()) ).thenReturn(mockBooks);
      this.mockMvc.perform( get("/books/year?year=1998") ).andDo( print() ).andExpect( status().isOk());
   }

   @Test
   void testGetBookByGenre() throws Exception {
      when(bookService.getBookByGenre(chinaTown.getGenre()) ).thenReturn(mockBooks);
      this.mockMvc.perform( get("/books/genre?genre=THRILLER") ).andDo( print() ).andExpect( status().isOk());
   }


   @Test
   void testSearchBook() throws Exception {

      when(bookService.books()).thenReturn(Collections.singletonList(theGreatGatsby));
      when(bookService.getBookByTitle(theGreatGatsby.getTitle())).thenReturn(Collections.singletonList(theGreatGatsby));
      when(bookService.getBookByAuthor(theGreatGatsby.getAuthor())).thenReturn(Collections.singletonList(theGreatGatsby));
      when(bookService.getBookByPublicationYear(theGreatGatsby.getYearOfPublication())).thenReturn(Collections.singletonList(theGreatGatsby));
      when(bookService.getBookByGenre(theGreatGatsby.getGenre())).thenReturn(Collections.singletonList(theGreatGatsby));


      mockMvc.perform(get("/books/search")
                      .param("title", "The Great Gatsby")
                      .param("author", "F. Scott Fitzgerald")
                      .param("yearOfPublication", "1925")
                      .param("genre", "FICTION"))
              .andDo(print())
              .andExpect(status().isOk())
              .andExpect(jsonPath("$", hasSize(4)))
              .andExpect(jsonPath("$[0].title", is("The Great Gatsby")))
              .andExpect(jsonPath("$[0].author", is("F. Scott Fitzgerald")))
              .andExpect(jsonPath("$[0].genre", is("FICTION")))
              .andExpect(jsonPath("$[0].yearOfPublication", is("1925")))
              .andExpect(jsonPath("$[0].isbn", is("978-0-7432-7356-5")))
              .andExpect(jsonPath("$[0].price", is(300.0)));
   }

}
