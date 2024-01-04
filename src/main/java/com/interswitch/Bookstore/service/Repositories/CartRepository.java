package com.interswitch.Bookstore.service.Repositories;

import com.interswitch.Bookstore.service.Models.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Long> {
}
