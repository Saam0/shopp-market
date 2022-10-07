package com.shopmarket.repositories;

import com.shopmarket.models.Picture;
import com.shopmarket.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PictureRepository extends JpaRepository<Picture,Long> {
    Picture findByProduct(Product product);

}
