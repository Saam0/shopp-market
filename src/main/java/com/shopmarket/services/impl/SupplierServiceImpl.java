package com.shopmarket.services.impl;

import com.shopmarket.models.Supplier;
import com.shopmarket.repositories.SupplierRepository;
import com.shopmarket.services.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class SupplierServiceImpl implements SupplierService {

    @Autowired
    private SupplierRepository supplierRepository;


    @Override
    public Supplier save(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    @Override
    public Optional<Supplier> findByName(String name) {
        return supplierRepository.findBySupplierName(name);
    }

    @Override
    public Optional<Supplier> findById(Long id) {
        return supplierRepository.findById(id);
    }

    @Override
    public Optional<Supplier> findBySupplierName(String supplierName) {
        return supplierRepository.findBySupplierName(supplierName);
    }

    @Override
    public List<Supplier> findAll() {
        return supplierRepository.findAll();
    }
}
