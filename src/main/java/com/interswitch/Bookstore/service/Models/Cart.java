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

import static com.interswitch.Bookstore.service.Utils.BookHelperMethods.totalAmountOfBooks;

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

//   @ManyToMany
//   @JoinTable(name = "cart_books", joinColumns = @JoinColumn(name = "cart_id"),inverseJoinColumns = @JoinColumn(name = "book_id"))
//   private List<Book> books;

   @OneToMany(mappedBy = "cart", cascade = CascadeType.PERSIST, orphanRemoval = true)
   private List<BookCartItem> bookItems = new ArrayList<>();


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
    * @param user           the user
    * @param bookItems      object containing the number of books in a cart and quantity of each book
    * @param paymentStatus  the payment status
    */
    public Cart(User user, List<BookCartItem> bookItems,  PaymentStatus paymentStatus) {
      this.user          = user;
      this.bookItems     = new ArrayList<>(bookItems);
      this.totalAmount   = totalAmountOfBooks(bookItems);
      this.paymentStatus = paymentStatus.PENDING;
   }

   /**
    * Instantiates a new Cart with two arguments.
    *
    * @param user      the user
    * @param bookItems object containing the number of books in a cart and quantity of each book
    */
    public Cart(User user, List<BookCartItem> bookItems) {
      this.user  = user;
      this.bookItems = new ArrayList<>(bookItems);
   }
}
