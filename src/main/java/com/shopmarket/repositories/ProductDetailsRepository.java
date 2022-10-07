package com.shopmarket.repositories;

import com.shopmarket.models.Product;
import com.shopmarket.models.ProductDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDetailsRepository extends JpaRepository<ProductDetails,Long> {
    ProductDetails findByProduct(Product product);
}
