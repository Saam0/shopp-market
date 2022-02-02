package com.shopmarket.services;

import com.shopmarket.models.catalog.GlobalType;
import com.shopmarket.models.catalog.Type;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CatalogService {
    List<GlobalType> findByName(String name);
    List<GlobalType> findAll();
    public List<Type> findAllType();
    Optional<GlobalType> findById(Long id);
    Optional<Type> findByTypeId(Long id);
    GlobalType save(GlobalType globalType);
}
