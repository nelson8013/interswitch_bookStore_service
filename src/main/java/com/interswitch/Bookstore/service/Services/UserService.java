package com.interswitch.Bookstore.service.Services;

import com.interswitch.Bookstore.service.Exceptions.UserExceptions.UserNotFoundException;
import com.interswitch.Bookstore.service.Interfaces.UserServiceInterface;
import com.interswitch.Bookstore.service.Models.User;
import com.interswitch.Bookstore.service.Repositories.UserRepository;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * The User service.
 *
 * @author Nelson Ekpenyong
 */
@Service
public class UserService implements UserServiceInterface {

   private final UserRepository  userRepository;
   private static final Logger LOGGER = LoggerFactory.getLogger(BookService.class);


   /**
    * Instantiates a new User service and injects the UserRepository in it and initializes the initializeUsers method.
    *
    * @param userRepository The user repository
    */
   public UserService(UserRepository userRepository) {
      this.userRepository = userRepository;
      initializeUsers();
   }




   /**
    * Initializes default users if the user repository is empty.
    * 4 Default users are created and saved to the repository for initial data setup.
    */
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


   /**
    * Retrieves a list of all users available in the book store Database.
    *
    * @return The list of all users in the system.
    */
    @Override
    public List<User> getUsers() {
      return userRepository.findAll();
   }



   /**
    * Retrieves a specific user based on the provided user ID.
    *
    * @param id The ID of the user to retrieve.
    * @return The user Id.
    * @throws UserNotFoundException If no user is found with the given Id.
    */
    @Override
    public User getUser(Long id) {
      return userRepository.findById(id).orElseThrow( ()-> new UserNotFoundException("This user doesn't exist"));
   }


}
