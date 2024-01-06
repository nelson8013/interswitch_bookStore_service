package com.interswitch.Bookstore.service.Repositories;

import com.interswitch.Bookstore.service.Models.Cart;
import com.interswitch.Bookstore.service.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
   List<Cart> findByUser_Id(Long userId);

   @Query("SELECT c FROM Cart c JOIN FETCH c.books WHERE c.user.id = :userId")
   List<Cart> findByUserIdWithBooks(@Param("userId") Long userId);
   Cart findByUser(User user);
}
