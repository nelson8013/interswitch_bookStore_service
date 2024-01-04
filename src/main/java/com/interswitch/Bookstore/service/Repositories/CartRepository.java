package com.interswitch.Bookstore.service.Repositories;

import com.interswitch.Bookstore.service.Models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
   List<Cart> findByUser_Id(Long userId);
}
