package com.shopmarket.services;

import com.shopmarket.models.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductService {

     Product save (Product product);
     Optional<Product> findById (Long id);
     List<Product> findByName (String name);
}
