package com.interswitch.Bookstore.service.Services;

import com.interswitch.Bookstore.service.Dtos.Responses.PurchaseHistory.PurchaseHistoryResponse;
import com.interswitch.Bookstore.service.Repositories.PurchaseHistoryRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PurchaseHistoryServiceTest {

   private AutoCloseable autoCloseable;

   @Mock
   private PurchaseHistoryRepository purchaseHistoryRepository;

   @Mock
   private UserService userService;

   @Mock
   private PurchaseHistoryService purchaseHistoryService;
   Long userId;
   List<Object[]> mockPurchaseHistory;

   @BeforeEach
   void setUp() {
      userId = 1l;
      autoCloseable = MockitoAnnotations.openMocks(this);

      mockPurchaseHistory = Arrays.asList(
              new Object[]{1L, 123L, 25.99, Timestamp.valueOf("2023-11-21 15:30:00"), "WEB"},
              new Object[]{2L, 456L, 19.95, Timestamp.valueOf("2023-11-15 10:00:00"), "USSD"}
      );

      purchaseHistoryService = new PurchaseHistoryService(purchaseHistoryRepository, userService);
   }

   @AfterEach
   void tearDown() {
      try {
         autoCloseable.close();
      } catch (Exception e) {
         e.printStackTrace();
      }
   }

   @Test
   void getPurchaseHistory() {
      when(purchaseHistoryRepository.findPurchaseHistoryByUserId(userId)).thenReturn(mockPurchaseHistory);

      List<PurchaseHistoryResponse> history = purchaseHistoryService.getPurchaseHistory(userId);

      verify(purchaseHistoryRepository).findPurchaseHistoryByUserId(userId);
   }
}