package com.shopmarket.repositories;

import com.shopmarket.models.catalog.SubType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubTypeRepository extends JpaRepository<SubType,Long> {
}
