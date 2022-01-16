package com.shopmarket.repositories;

import com.shopmarket.models.catalog.GlobalType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GlobalTypeRepository extends JpaRepository<GlobalType,Long> {

    List<GlobalType> findByGlobalTypeName(String name);
}
