package com.shopmarket.services;

import com.shopmarket.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductService {


     Product save(Product product, MultipartFile file);
     Product update(Product product,Long productId, MultipartFile file);
     void delete(Long productId);
     Optional<Product> findById (Long id);
     List<Product> findByName (String name);
     List<Product> findAll ();
     Page<Product> findPage(int pageNumber);
     List<Product> findByKeyword(String keyword);
     Page<Product> findAllWithSort(String field, String direction, int pageNumber);

}
