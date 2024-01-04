package com.interswitch.Bookstore.service.Repositories;

import com.interswitch.Bookstore.service.Models.PurchaseHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseHistoryRepository extends JpaRepository<PurchaseHistory, Long> {
}
