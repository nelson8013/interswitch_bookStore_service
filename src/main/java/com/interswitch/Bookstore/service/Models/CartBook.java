package com.interswitch.Bookstore.service.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "cart_books")
public class CartBook {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @ManyToOne
   @JoinColumn(name = "book_id")
   private Book book;

   @ManyToOne
   @JoinColumn(name = "cart_id")
   @JsonIgnore
   private Cart cart;

   @Column(columnDefinition = "int default 0")
   private int quantity;


   public CartBook(){}
   public CartBook(Book book, Cart cart, int quantity) {
      this.book     = book;
      this.cart     = cart;
      this.quantity = quantity;
   }


}
