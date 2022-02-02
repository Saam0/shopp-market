package com.shopmarket.services;

import com.shopmarket.models.Supplier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface SupplierService {
    Supplier save (Supplier supplier);
    Optional<Supplier> findByName(String name);
    Optional<Supplier> findById(Long id);
    List<Supplier> findAll();
}
