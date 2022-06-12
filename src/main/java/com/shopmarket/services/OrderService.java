package com.shopmarket.services;

import com.shopmarket.models.Cart;
import com.shopmarket.models.Order;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {
    Order saveOrder( String userEmail);
}
