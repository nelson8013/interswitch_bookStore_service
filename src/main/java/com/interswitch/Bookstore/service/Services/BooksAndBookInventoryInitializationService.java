package com.interswitch.Bookstore.service.Services;

import com.interswitch.Bookstore.service.Enums.BookGenre;
import com.interswitch.Bookstore.service.Interfaces.BooksAndBookInventoryInitializationServiceInterface;
import com.interswitch.Bookstore.service.Models.Book;
import com.interswitch.Bookstore.service.Models.BookInventory;
import com.interswitch.Bookstore.service.Repositories.BookInventoryRepository;
import com.interswitch.Bookstore.service.Repositories.BookRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@Transactional
public class BooksAndBookInventoryInitializationService implements BooksAndBookInventoryInitializationServiceInterface {
   private final BookRepository bookRepository;

   private final BookInventoryRepository bookInventoryRepository;

   private static final Logger LOGGER = LoggerFactory.getLogger(BooksAndBookInventoryInitializationService.class);


   public BooksAndBookInventoryInitializationService(BookRepository bookRepository, BookInventoryRepository bookInventoryRepository){
      this.bookRepository = bookRepository;
      this.bookInventoryRepository = bookInventoryRepository;
      initializeBooksAndBookInventory();
   }

   /**
    * This method is initializes the books and book inventory tables with 10 default entries.
    */
   @Override
   @PostConstruct
   public void initializeBooksAndBookInventory() {
      if (bookInventoryRepository.count() == 0) {
         Book chinaTown           = new Book("china Town",         BookGenre.THRILLER, "789-0-4341-1347-0", "Jackie Chan", "1998", 350.0);
         Book theGreatGatsby      = new Book("The Great Gatsby",   BookGenre.FICTION, "978-0-7432-7356-5", "F. Scott Fitzgerald", "1925", 300.0);
         Book intoTheWild         = new Book("Into the Wild",      BookGenre.FICTION, "978-0-385-48680-2", "Jon Krakauer", "1996", 300.0);
         Book orientExpressMurder = new Book("Murder on the Orient Express", BookGenre.MYSTERY, "978-0-06-2693662", "Agatha Christie", "1934", 659.0);
         Book theHobbit           = new Book("The Hobbit",         BookGenre.FICTION, "978-0-618-63420-0", "J.R.R. Tolkien", "1937", 522.0);
         Book theRaven            = new Book("The Raven",          BookGenre.POETRY, "978-1-84749-310-3", "Edgar Allan Poe", "1845", 522.0);
         Book it                  = new Book("It",                 BookGenre.HORROR, "978-0451169518", "Stephen King", "1986", 800.0);
         Book prideAndPrejudice   = new Book("Pride and Prejudice",BookGenre.FICTION, "978-1-85326-000-9", "Jane Austen", "1813", 429.0);
         Book catch22             = new Book("Catch-22",           BookGenre.SATIRE, "978-0-684-85358-7", "Joseph Heller", "1961", 429.0);
         Book tattooGirl          = new Book("The Girl with the Dragon Tattoo", BookGenre.THRILLER, "978-0-307-45440-6", "Stieg Larsson", "2005", 750.0);

         bookRepository.saveAll(Arrays.asList(
                 chinaTown, theGreatGatsby, intoTheWild, orientExpressMurder,
                 theHobbit, theRaven, it, prideAndPrejudice, catch22, tattooGirl
         ));

         LOGGER.info("Initialized default books");


         BookInventory chinaTownInv           = new BookInventory(chinaTown,           23);
         BookInventory theGreatGatsbyInv      = new BookInventory(theGreatGatsby,      12);
         BookInventory intoTheWildInv         = new BookInventory(intoTheWild,          4);
         BookInventory orientExpressMurderInv = new BookInventory(orientExpressMurder, 21);
         BookInventory theHobbitInv           = new BookInventory(theHobbit,            5);
         BookInventory theRavenInv            = new BookInventory(theRaven,             8);
         BookInventory itInv                  = new BookInventory(it,                   3);
         BookInventory prideAndPrejudiceInv   = new BookInventory(prideAndPrejudice,   62);
         BookInventory catch22Inv             = new BookInventory(catch22,             54);
         BookInventory tattooGirlInv          = new BookInventory(tattooGirl,          80);


         bookInventoryRepository.saveAll(Arrays.asList(
                 chinaTownInv, theGreatGatsbyInv, intoTheWildInv, orientExpressMurderInv,
                 theHobbitInv, theRavenInv, itInv, prideAndPrejudiceInv, catch22Inv, tattooGirlInv
         ));

         LOGGER.info("Initialized book inventory table with default inventory");

      }
   }
}
