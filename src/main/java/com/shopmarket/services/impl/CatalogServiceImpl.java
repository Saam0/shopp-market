package com.shopmarket.services.impl;

import com.shopmarket.models.catalog.GlobalType;
import com.shopmarket.models.catalog.Type;
import com.shopmarket.repositories.GlobalTypeRepository;
import com.shopmarket.repositories.SubTypeRepository;
import com.shopmarket.repositories.TypeRepository;
import com.shopmarket.services.CatalogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CatalogServiceImpl implements CatalogService {
    @Autowired
    private GlobalTypeRepository globalTypeRepository;
    @Autowired
    private SubTypeRepository subTypeRepository;
    @Autowired
    private TypeRepository typeRepository;


    @Override
    public List<GlobalType> findByName(String name) {
        return globalTypeRepository.findByGlobalTypeName(name);
    }

    @Override
    public List<GlobalType> findAll() {
        return globalTypeRepository.findAll();
    }

    @Override
    public Optional<GlobalType> findById(Long id) {
        return globalTypeRepository.findById(id);
    }

    @Override
    public GlobalType save(GlobalType globalType) {
        return globalTypeRepository.save(globalType);
    }

    @Override
    public List<Type> findAllType() {
        return typeRepository.findAll();
    }


}
