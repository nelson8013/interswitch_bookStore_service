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

   @OneToMany(mappedBy = "cart")
   private List<CartBook> cartbooks =  new ArrayList<>();
   private Double totalAmount;

   @Enumerated(EnumType.STRING)
   private PaymentStatus paymentStatus;


   @Column(columnDefinition = "tinyint default 1")
   private boolean isActive = true;

   @CreationTimestamp
   private LocalDateTime createdAt;

   @UpdateTimestamp
   private LocalDateTime updatedAt;


   /**
    * Instantiates a new Cart without arguments.
    */
   public Cart() {}



   /**
    * Instantiates a new Cart with 6 arguments.
    *
    * @param id            the id of the cart
    * @param user          the user associated witht the cart
    * @param cartbooks     the cartbooks in the cart
    * @param totalAmount   the total amount of all books in the cart
    * @param paymentStatus the payment status of the cart
    * @param isActive      the current state of a cart
    */
   public Cart(Long id, User user, List<CartBook> cartbooks, Double totalAmount,PaymentStatus paymentStatus, Boolean isActive) {
      this.id            = id;
      this.user          = user;
      this.cartbooks     = new ArrayList<>(cartbooks);
      this.totalAmount   = totalAmount;
      this.paymentStatus = PaymentStatus.PENDING;
      this.isActive      = true;
   }

   /**
    * Instantiates a new Cart with 5 arguments.
    *
    * @param user          the user associated witht the cart
    * @param cartbooks     the cartbooks in the cart
    * @param totalAmount   the total amount of all books in the cart
    * @param paymentStatus the payment status of the cart
    * @param isActive      the current state of a cart
    */
   public Cart(User user, List<CartBook> cartbooks, Double totalAmount,PaymentStatus paymentStatus, Boolean isActive) {
      this.user          = user;
      this.cartbooks     = new ArrayList<>(cartbooks);
      this.totalAmount   = totalAmount;
      this.paymentStatus = PaymentStatus.PENDING;
      this.isActive      = true;
   }

   /**
    * Instantiates a new Cart with two arguments.
    *
    * @param user  the user
    * @param books the books
    */
   public Cart(User user, List<CartBook> cartbooks) {
      this.user  = user;
      this.cartbooks = new ArrayList<>(cartbooks);
   }
}