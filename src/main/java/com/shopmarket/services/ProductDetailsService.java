package com.shopmarket.services;

import com.shopmarket.models.Product;
import com.shopmarket.models.ProductDetails;
import com.shopmarket.models.Stock;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface ProductDetailsService {
    ProductDetails save(ProductDetails productDetails);
    ProductDetails update(ProductDetails productDetails, Long productDetailsId);
    ProductDetails findByProduct(Product product);
    Optional<ProductDetails> findById(Long id);
}
