package com.interswitch.Bookstore.service.Models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class BookCartItem {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @ManyToOne
   @JoinColumn(name = "cart_id")
   private Cart cart;


   @ManyToOne
   @JoinColumn(name = "book_id")
   private Book book;
   private int quantity;

   public BookCartItem(){}
   public BookCartItem(Book book, Cart cart, int quantity) {
      this.book     = book;
      this.cart     = cart;
      this.quantity = quantity;
   }

}
