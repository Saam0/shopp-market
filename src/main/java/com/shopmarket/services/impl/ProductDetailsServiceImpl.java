package com.shopmarket.services.impl;

import com.shopmarket.models.Product;
import com.shopmarket.models.ProductDetails;
import com.shopmarket.repositories.ProductDetailsRepository;
import com.shopmarket.services.ProductDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProductDetailsServiceImpl implements ProductDetailsService {
    @Autowired
    private ProductDetailsRepository productDetailsRepository;


    @Override
    public ProductDetails save(ProductDetails productDetails) {
        return productDetailsRepository.save(productDetails);
    }

    @Override
    public ProductDetails update(ProductDetails productDetails, Long productDetailsId) {
        ProductDetails productDetailsToBeUpdated = findById(productDetailsId).get();
        productDetailsToBeUpdated.setProductDescription(productDetails.getProductDescription());
        productDetailsToBeUpdated.setComposition(productDetails.getComposition());
        productDetailsToBeUpdated.setCountry(productDetails.getCountry());
        productDetailsToBeUpdated.setManufacturer(productDetails.getManufacturer());
        productDetailsToBeUpdated.setStorageConditions(productDetails.getStorageConditions());
        productDetailsToBeUpdated.setTypeOfPackaging(productDetails.getTypeOfPackaging());
        productDetailsToBeUpdated.setWeight(productDetails.getWeight());
        return productDetailsRepository.save(productDetailsToBeUpdated);
    }

    @Override
    public ProductDetails findByProduct(Product product) {
        return productDetailsRepository.findByProduct(product);
    }

    @Override
    public Optional<ProductDetails> findById(Long id) {
        return productDetailsRepository.findById(id);
    }
}
