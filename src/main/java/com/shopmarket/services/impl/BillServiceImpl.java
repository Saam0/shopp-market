package com.shopmarket.services.impl;

import com.shopmarket.models.Bill;
import com.shopmarket.models.Order;
import com.shopmarket.repositories.BillRepository;
import com.shopmarket.services.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillServiceImpl implements BillService {
    @Autowired
    private BillRepository billRepository;

    @Override
    public Bill getBill(Order order) {
        return billRepository.findByOrder(order);
    }
}
