package com.shopmarket.repositories;

import com.shopmarket.models.Product;
import com.shopmarket.models.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StockRepository extends JpaRepository<Stock,Long> {
    Optional<Stock> findByProduct(Product product);

}
