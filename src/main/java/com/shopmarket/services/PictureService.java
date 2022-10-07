package com.shopmarket.services;

import com.shopmarket.models.Picture;
import com.shopmarket.models.Product;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
public interface PictureService {

    Picture save(MultipartFile file);

    Picture update( MultipartFile file, Long productId);
    Picture findByProduct(Product product);

    Optional<Picture> findById(Long id);

}
