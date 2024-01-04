package com.interswitch.Bookstore.service.Services;

import com.interswitch.Bookstore.service.Enums.BookGenre;
import com.interswitch.Bookstore.service.Interfaces.UserServiceInterface;
import com.interswitch.Bookstore.service.Models.Book;
import com.interswitch.Bookstore.service.Models.User;
import com.interswitch.Bookstore.service.Repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserService implements UserServiceInterface {

   private final UserRepository  userRepository;
   private static final Logger LOGGER = LoggerFactory.getLogger(BookService.class);


   public UserService(UserRepository userRepository) {
      this.userRepository = userRepository;
      initializeUsers();
   }

   @Override
   @PostConstruct
   public void initializeUsers() {

      if (userRepository.count() == 0) {
         User sam     = new User("Sam","Dolapo", "samdol@mailnator.com", "TYUIO567");
         User adeniyi = new User("Adeniyi","Afolabi", "adfol67@mailnator.com", "TYUIO568");
         User jesse   = new User("Jesse","Finn", "jessefinn@mailnator.com", "TYUIO569");
         User tunde   = new User("Babaunde","Iseni", "babaT@mailnator.com", "TYUIO510");

         userRepository.saveAll(Arrays.asList(sam, adeniyi, jesse, tunde));

         LOGGER.info("Initialized default users");
      }

   }
}
