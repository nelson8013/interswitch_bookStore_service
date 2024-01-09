package com.interswitch.Bookstore.service.Controllers;

import com.interswitch.Bookstore.service.Dtos.Responses.PurchaseHistory.PurchaseHistoryResponse;
import com.interswitch.Bookstore.service.Services.PurchaseHistoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PurchaseHistoryController {
   private final PurchaseHistoryService purchaseHistoryService;


   public PurchaseHistoryController(PurchaseHistoryService purchaseHistoryService) {
      this.purchaseHistoryService = purchaseHistoryService;
   }

   @GetMapping("/purchase-history/{userId}")
   public
   ResponseEntity <List<PurchaseHistoryResponse>> getPurchaseHistory(@PathVariable Long userId) {
      List<PurchaseHistoryResponse> purchaseHistory = purchaseHistoryService.getPurchaseHistory(userId);
      return new ResponseEntity<>(purchaseHistory, HttpStatus.OK);
   }
}
