package com.interswitch.Bookstore.service.Repositories;

import com.interswitch.Bookstore.service.Models.BookInventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookInventoryRepository extends JpaRepository<BookInventory, Long> {
}
