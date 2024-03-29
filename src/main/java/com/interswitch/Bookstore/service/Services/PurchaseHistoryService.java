package com.interswitch.Bookstore.service.Services;

import com.interswitch.Bookstore.service.Dtos.Responses.PurchaseHistory.PurchaseHistoryResponse;
import com.interswitch.Bookstore.service.Interfaces.PurchaseHistoryInterface;
import com.interswitch.Bookstore.service.Repositories.PurchaseHistoryRepository;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;



@Service
public class PurchaseHistoryService implements PurchaseHistoryInterface {

   private final PurchaseHistoryRepository purchaseHistoryRepository;

   private UserService userService;


   public PurchaseHistoryService(PurchaseHistoryRepository purchaseHistoryRepository, UserService userService) {
      this.purchaseHistoryRepository = purchaseHistoryRepository;
      this.userService = userService;
   }

   /**
    * @param userId
    * @return list of a user's purchase history
    */
   @Override
   public List<PurchaseHistoryResponse> getPurchaseHistory(Long userId) {
      List<Object[]> rawResults = purchaseHistoryRepository.findPurchaseHistoryByUserId(userId);

      return rawResults.stream()
              .map(result -> new PurchaseHistoryResponse(
                      (Long) result[0],
                      Math.toIntExact((Long) result[1]),
                      (Double) result[2],
                      ((Timestamp) result[3]).toLocalDateTime(),
                      (String) result[4]
              ))
              .collect(Collectors.toList());
   }


}
