package com.shopmarket.services.impl;

import com.shopmarket.models.Product;
import com.shopmarket.models.Stock;
import com.shopmarket.repositories.StockRepository;
import com.shopmarket.services.StockService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class StockServiceImpl implements StockService {
    @Autowired
    private StockRepository stockRepository;


    @Override
    public Stock save(Stock stock) {
        return stockRepository.save(stock);
    }

    @Override
    public List<Stock> findByProductId(Product id) {
        return null;
    }

    @Override
    public Optional<Stock> findById(Long id) {
        return Optional.empty();
    }
}
