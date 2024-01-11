package com.interswitch.Bookstore.service.Models;

import com.interswitch.Bookstore.service.Enums.BookGenre;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Book {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   private String title;
   private BookGenre genre;
   private String isbn;
   private String author;
   private String yearOfPublication;

   private Double price;

   @CreationTimestamp
   private LocalDateTime createdAt;

   @UpdateTimestamp
   private LocalDateTime updatedAt;


   public Book() {}

   public Book(String title, BookGenre genre, String isbn, String author, String yearOfPublication, Double price) {
      this.title  = title;
      this.genre  = genre;
      this.isbn   = isbn;
      this.author = author;
      this.yearOfPublication = yearOfPublication;
      this.price  = price;
      this.createdAt = LocalDateTime.now();
   }


   public Book(Long id, String title, BookGenre genre, String isbn, String author, String yearOfPublication, Double price) {
      this.id     = id;
      this.title  = title;
      this.genre  = genre;
      this.isbn   = isbn;
      this.author = author;
      this.yearOfPublication = yearOfPublication;
      this.price  = price;
      this.createdAt = LocalDateTime.now();
   }

}
