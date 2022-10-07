package com.shopmarket.services.impl;

import com.shopmarket.models.Product;
import com.shopmarket.models.Stock;
import com.shopmarket.repositories.StockRepository;
import com.shopmarket.services.ProductService;
import com.shopmarket.services.StockService;
import com.shopmarket.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
@Service
public class StockServiceImpl implements StockService {
    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private SupplierService supplierService;


    @Override
    public Stock save(Stock stock) {
        return stockRepository.save(stock);
    }

    @Override
    public Stock update(Stock stock, Long stockId) {
        Stock stockToBeUpdated = findById(stockId).get();
        stockToBeUpdated.setPurchasePrice(stock.getPurchasePrice());
        stockToBeUpdated.setQuantity(stock.getQuantity());
        stockToBeUpdated.setPurchaseDate(stock.getPurchaseDate());
        stockToBeUpdated.setUnitOfMeasurement(stock.getUnitOfMeasurement());
        stockToBeUpdated.setSupplier(supplierService.findByName(stock.getSupplier().getSupplierName()).get());
        return stockRepository.save(stockToBeUpdated);
    }
    @Transactional
    @Override
    public void delete(Stock stock){
        stockRepository.delete(stock);
    }
    @Override
    public Optional<Stock> findByProduct(Product product) {
        return stockRepository.findByProduct(product);
    }

    @Override
    public Optional<Stock> findById(Long id) {
        return stockRepository.findById(id);
    }
}
