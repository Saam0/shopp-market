package com.shopmarket.services;

import com.shopmarket.models.Cart;
import com.shopmarket.models.Order;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface OrderService {
    Order saveOrder(String userEmail, String ccNumber);

    List<Order> findAll();

}
