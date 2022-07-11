package com.shopmarket.services;

import com.shopmarket.models.Bill;
import com.shopmarket.models.Order;
import org.springframework.stereotype.Service;

@Service
public interface BillService {
    Bill getBill(Order order);
}
