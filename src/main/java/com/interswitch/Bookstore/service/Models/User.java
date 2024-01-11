package com.interswitch.Bookstore.service.Models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class User {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @NotEmpty( message = "First Name cannot be null")
   private String firstName;

   @NotEmpty( message = "Last Name cannot be null")
   private String lastName;

   @Email( message = "Please enter a valid email address. No vex!")
   @NotBlank( message = "Email cannot be null")
   @Column( unique = true)
   private String email;

   private String password;

   @CreationTimestamp
   private LocalDateTime createdAt;

   @UpdateTimestamp
   private LocalDateTime updatedAt;


   public User(){}

   public User(Long id, String firstName, String lastName, String email, String password) {
      this.id        = id;
      this.firstName = firstName;
      this.lastName  = lastName;
      this.email     = email;
      this.password  = password;
      this.createdAt = LocalDateTime.now();
   }

   public User(String firstName, String lastName, String email, String password) {
      this.firstName = firstName;
      this.lastName  = lastName;
      this.email     = email;
      this.password  = password;
      this.createdAt = LocalDateTime.now();
   }
}
