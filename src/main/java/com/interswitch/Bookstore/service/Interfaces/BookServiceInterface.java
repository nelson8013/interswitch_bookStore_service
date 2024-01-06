package com.interswitch.Bookstore.service.Interfaces;

import com.interswitch.Bookstore.service.Enums.BookGenre;
import com.interswitch.Bookstore.service.Models.Book;

import java.util.List;
import java.util.Optional;

public interface BookServiceInterface {
   List<Book> books();
   Book book(Long id);
   List<Book> getBookByTitle(String title);
   List<Book> getBookByAuthor(String author);
   List<Book> getBookByPublicationYear(String year);
   List<Book> getBookByGenre(BookGenre genre);

}
