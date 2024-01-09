package com.interswitch.Bookstore.service.Interfaces;

import com.interswitch.Bookstore.service.Dtos.Responses.PurchaseHistory.PurchaseHistoryResponse;

import java.util.List;

public interface PurchaseHistoryInterface {
   List<PurchaseHistoryResponse> getPurchaseHistory(Long userId);
}
