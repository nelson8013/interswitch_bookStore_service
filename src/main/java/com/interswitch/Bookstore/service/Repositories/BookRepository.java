package com.interswitch.Bookstore.service.Repositories;

import com.interswitch.Bookstore.service.Enums.BookGenre;
import com.interswitch.Bookstore.service.Models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
   Optional<Book> findByTitle(String title);
   Optional<Book> findByAuthor(String author);
   Optional<Book> findByPublicationYear(String year);
   List<Book> findByGenre(BookGenre genre);

   /*
    * //Todo: Nelson, try and recreate this query using JPA keywords
    *  SELECT b.* FROM Book b
    *  WHERE LOWER(b.title) LIKE LOWER(CONCAT('%', :searchTerm, '%'))
    *  OR LOWER(b.author) LIKE LOWER(CONCAT('%', :searchTerm, '%'))
    *  OR LOWER(b.yearOfPublication) LIKE LOWER(CONCAT('%', :searchTerm, '%'))
    *  OR LOWER(b.genre) LIKE LOWER(CONCAT('%', :searchTerm, '%'));
    *
    * */


   List<Book> findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCaseOrYearOfPublicationContainingIgnoreCaseOrGenreContainingIgnoreCase(String searchTerm);

   @Query("SELECT b FROM Book b WHERE LOWER(b.title) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(b.author) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(b.yearOfPublication) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR LOWER(b.genre) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
   List<Book> searchBooks(@Param("searchTerm") String searchTerm);
}
