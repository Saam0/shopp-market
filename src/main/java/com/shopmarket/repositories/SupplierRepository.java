package com.shopmarket.repositories;

import com.shopmarket.models.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier,Long> {
    Optional<Supplier> findBySupplierName(String name);
}
