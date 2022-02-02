package com.shopmarket.services;

import com.shopmarket.models.Product;
import com.shopmarket.models.Stock;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface StockService {
    Stock save(Stock stock);
    List<Stock> findByProductId(Product id);
    Optional<Stock> findById(Long id);

}
