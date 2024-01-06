package com.interswitch.Bookstore.service.Services;

import com.interswitch.Bookstore.service.Enums.BookGenre;
import com.interswitch.Bookstore.service.Exceptions.BookInventoryExceptions.BookInventoryNotFoundException;
import com.interswitch.Bookstore.service.Interfaces.BookInventoryServiceInterface;
import com.interswitch.Bookstore.service.Models.Book;
import com.interswitch.Bookstore.service.Models.BookInventory;
import com.interswitch.Bookstore.service.Repositories.BookInventoryRepository;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;


/**
 * The Book Inventory Service
 *
 * @author Nelson Ekpenyong
 */
@Service
public class BookInventoryService implements BookInventoryServiceInterface {

    private final BookInventoryRepository bookInventoryRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(BookInventoryService.class);


   /**
    * Instantiates a new Book service with the BookRepository and the book inventory's initializer.
    *
    * @param bookInventoryRepository the book repository
    */
    public BookInventoryService(BookInventoryRepository bookInventoryRepository) {
      this.bookInventoryRepository = bookInventoryRepository;
   }


   /**
    * Gets records in the book inventory table
    *
    * @return list of inventory records
    */
    @Override
    public List<BookInventory> inventory() {
      return bookInventoryRepository.findAll();
   }



   /**
    * Gets a single record in the book inventory table
    *
    * @param id
    * @return a single book inventory object given an ID
    * @throws BookInventoryNotFoundException  If the inventory is not found.
    */
    @Override
    public BookInventory inventory(Long id) {
      return bookInventoryRepository.findById(id)
              .orElseThrow( () -> new BookInventoryNotFoundException("The Inventory for the requested book does not exist"));
   }



   /**
    * Gets the book quantity
    *
    * @param bookId
    * @return The number of books left for a given bookId
    */
    @Override
    public Integer getBookQuantity(Long bookId) {
      return bookInventoryRepository.findQuantityByBookId(bookId);
   }



   /**
    * Updates the book quantity after a purchase is made.
    *
    * @param bookId
    * @param quantity
    */
    @Override
    public void updateBookQuantity(Long bookId, int quantity) {
      BookInventory inventory = bookInventoryRepository.findByBookId(bookId);
      inventory.setQuantity(quantity);
      bookInventoryRepository.save(inventory);
   }



   /**
    * Checks if an inventory exists by a given bookId.
    *
    * @param bookId
    * @return True if the inventory exists buy the given bookId or false otherwise.
    */
    @Override
    public boolean doesBookExist(Long bookId) {
      return bookInventoryRepository.existsByBookId(bookId);
   }
}
