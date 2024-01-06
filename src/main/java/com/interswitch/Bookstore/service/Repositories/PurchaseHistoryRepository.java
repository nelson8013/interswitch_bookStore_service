package com.interswitch.Bookstore.service.Repositories;

import com.interswitch.Bookstore.service.Dtos.Responses.PurchaseHistoryResponse;
import com.interswitch.Bookstore.service.Models.PurchaseHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public interface PurchaseHistoryRepository extends JpaRepository<PurchaseHistory, Long> {

//   @Query(value = "SELECT " +
//           "u.id as user_id, " +
//           "COUNT(cb.book_id) as number_of_books, " +
//           "SUM(b.price) as total_amount, " +
//           "MAX(c.created_at) as transaction_time, " +
//           "c.payment_status as payment_method " +
//           "FROM cart c " +
//           "JOIN cart_books cb ON c.id = cb.cart_id " +
//           "JOIN book b ON cb.book_id = b.id " +
//           "JOIN user u ON c.user_id = u.id " +
//           "WHERE c.user_id = :userId AND c.payment_status = 'SUCCESS' " +
//           "GROUP BY u.id, c.payment_status, c.created_at", nativeQuery = true)
//   List<Object[]> findPurchaseHistoryByUserId(Long userId);




//      @Query(value = "SELECT " +
//           "u.id as user_id, " +
//           "COUNT(cb.book_id) as number_of_books, " +
//           "SUM(b.price) as total_amount, " +
//           "MAX(c.created_at) as transaction_time, " +
//           "c.payment_status as payment_method " +
//           "FROM cart c " +
//           "JOIN cart_books cb ON c.id = cb.cart_id " +
//           "JOIN book b ON cb.book_id = b.id " +
//           "JOIN user u ON c.user_id = u.id " +
//           "WHERE c.user_id = :userId AND c.payment_status = 'SUCCESS' " +
//           "GROUP BY u.id, c.payment_status, c.created_at", nativeQuery = true)
//   List<PurchaseHistoryResponse> findPurchaseHistoryByUserId(Long userId);


   @Query(value = "SELECT " +
           "u.id as user_id, " +
           "COUNT(cb.book_id) as number_of_books, " +
           "SUM(b.price) as total_amount, " +
           "MAX(c.created_at) as transaction_time, " +
           "c.payment_status as payment_method " +
           "FROM cart c " +
           "JOIN cart_books cb ON c.id = cb.cart_id " +
           "JOIN book b ON cb.book_id = b.id " +
           "JOIN user u ON c.user_id = u.id " +
           "WHERE c.user_id = :userId AND c.payment_status = 'SUCCESS' " +
           "GROUP BY u.id, c.payment_status, c.created_at", nativeQuery = true)
   List<Object[]> findPurchaseHistoryByUserId(Long userId);


}
