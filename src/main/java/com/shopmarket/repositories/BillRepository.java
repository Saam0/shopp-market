package com.shopmarket.repositories;

import com.shopmarket.models.Bill;
import com.shopmarket.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillRepository extends JpaRepository<Bill,Long> {
    Bill findByOrder(Order order);
}
