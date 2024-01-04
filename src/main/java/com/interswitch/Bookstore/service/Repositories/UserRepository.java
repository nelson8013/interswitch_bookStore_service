package com.interswitch.Bookstore.service.Repositories;

import com.interswitch.Bookstore.service.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
