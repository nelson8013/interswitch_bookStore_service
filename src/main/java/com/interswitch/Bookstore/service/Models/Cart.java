package com.interswitch.Bookstore.service.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
public class Cart {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @OneToOne(cascade = CascadeType.ALL)
   @JoinColumn(name = "user_id")
   private User user;

   @ManyToMany
   @JoinTable(
           name = "cart_books",
           joinColumns = @JoinColumn(name = "cart_id"),
           inverseJoinColumns = @JoinColumn(name = "book_id"))
   private List<Book> books;

   private Double totalAmount;

   @CreationTimestamp
   private LocalDateTime createdAt;

   @UpdateTimestamp
   private LocalDateTime updatedAt;


   public Cart() {}

   public Cart(User user, List<Book> books, Double totalAmount) {
      this.user        = user;
      this.books       = books;
      this.totalAmount = totalAmount;
   }

   public Cart(User user, List<Book> books) {
      this.user  = user;
      this.books = books;
   }
}
