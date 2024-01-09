package com.interswitch.Bookstore.service.Repositories;

import com.interswitch.Bookstore.service.Models.CartBook;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartBookRepository extends JpaRepository<CartBook, Long> {
}
