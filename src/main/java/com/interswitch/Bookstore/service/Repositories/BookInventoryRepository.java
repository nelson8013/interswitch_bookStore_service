package com.interswitch.Bookstore.service.Repositories;

import com.interswitch.Bookstore.service.Models.BookInventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookInventoryRepository extends JpaRepository<BookInventory, Long> {

   BookInventory findByBookId(Long bookId);

   @Query("SELECT bi.quantity FROM BookInventory bi WHERE bi.book.id =  :bookId")
   Integer findQuantityByBookId(Long bookId);

   boolean existsByBookId(Long bookId);
}
