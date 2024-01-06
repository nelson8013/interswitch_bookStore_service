package com.interswitch.Bookstore.service.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class BookInventory {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @OneToOne
   @JoinColumn(name = "book_id")
   private Book book;

   private Integer quantity;

   @CreationTimestamp
   private LocalDateTime createdAt;

   @UpdateTimestamp
   private LocalDateTime updatedAt;


   public BookInventory(){}

   public BookInventory(Book book, Integer quantity) {
      this.book      = book;
      this.quantity  = quantity;
      this.createdAt = LocalDateTime.now();
   }
}
