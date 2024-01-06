package com.interswitch.Bookstore.service.Models;

import com.interswitch.Bookstore.service.Enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Cart.
 */
@Entity
@Getter
@Setter
public class Cart {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @OneToOne
   @JoinColumn(name = "user_id")
   private User user;

   @ManyToMany
   @JoinTable(name = "cart_books", joinColumns = @JoinColumn(name = "cart_id"),inverseJoinColumns = @JoinColumn(name = "book_id"))
   private List<Book> books;
   private Double totalAmount;

   @Enumerated(EnumType.STRING)
   private PaymentStatus paymentStatus;

   @CreationTimestamp
   private LocalDateTime createdAt;

   @UpdateTimestamp
   private LocalDateTime updatedAt;


   /**
    * Instantiates a new Cart without arguments.
    */
   public Cart() {}

   /**
    * Instantiates a new Cart with 4 arguments.
    *
    * @param user          the user
    * @param books         the books
    * @param totalAmount   the total amount
    * @param paymentStatus the payment status
    */
   public Cart(User user, List<Book> books, Double totalAmount,PaymentStatus paymentStatus) {
      this.user          = user;
      this.books         = new ArrayList<>(books); //I am init this collection field here, to avoid null pointer exceptions
      this.totalAmount   = totalAmount;
      this.paymentStatus = PaymentStatus.PENDING;
   }

   /**
    * Instantiates a new Cart with two arguments.
    *
    * @param user  the user
    * @param books the books
    */
   public Cart(User user, List<Book> books) {
      this.user  = user;
      this.books = new ArrayList<>(books);
   }
}
