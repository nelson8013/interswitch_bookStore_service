package com.interswitch.Bookstore.service.Interfaces;

import com.interswitch.Bookstore.service.Dtos.Responses.PurchaseHistoryResponse;
import com.interswitch.Bookstore.service.Models.PurchaseHistory;

import java.util.List;

public interface PurchaseHistoryInterface {
   List<PurchaseHistoryResponse> getPurchaseHistory(Long userId);
}
