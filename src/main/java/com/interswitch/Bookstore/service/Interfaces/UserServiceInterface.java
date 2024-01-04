package com.interswitch.Bookstore.service.Interfaces;

import com.interswitch.Bookstore.service.Models.User;

import java.util.List;

public interface UserServiceInterface {
   void initializeUsers();

   List<User> getUsers();
   User getUser(Long id);

}
