package com.shopmarket.repositories;

import com.shopmarket.models.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByProductName(String name);
//    @Query(value = "select p from Product p where " +
//            "p.productName like %?1% or p.stock.supplier.supplierName like %?1%" )
//    List<Product> findByKeyword(String keyword);

    @Query(value = "select p from Product p where " +
            "p.productName like %?1%" )
    List<Product> findByKeyword(String keyword);
//
//    @Query(value = "select p from Product p where " +
//            "p.productName like %?1%" )
//    Page<Product> findPageByKeyword(String keyword, Pageable pageable);

}
