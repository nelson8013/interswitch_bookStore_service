package com.interswitch.Bookstore.service.Interfaces;

import com.interswitch.Bookstore.service.Enums.BookGenre;
import com.interswitch.Bookstore.service.Models.Book;

import java.util.List;
import java.util.Optional;

public interface BookServiceInterface {
   void initializeBooks();
   List<Book> books();
   Book book(Long id);
   Book getBookByTitle(String title);
   Book getBookByAuthor(String author);
   Book getBookByPublicationYear(String year);
   List<Book> getBookByGenre(BookGenre genre);
   List<Book> searchBooks(String searchTerm);

   List<Book> searchBooksTwo(String searchTerm);
}
