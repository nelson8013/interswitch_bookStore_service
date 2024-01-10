package com.interswitch.Bookstore.service.Interfaces;

import com.interswitch.Bookstore.service.Models.BookInventory;

import java.util.List;

public interface BookInventoryServiceInterface {
   List<BookInventory> inventories();
   BookInventory inventory(Long id);
   Integer getBookQuantity(Long bookId);
   void updateBookQuantity(Long bookId, int quantity);
   boolean doesBookExist(Long bookId);
}
