package com.shopmarket.services;

import com.shopmarket.models.Address;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AddressService {
    Address save(Address address);
    List<Address> findByUserName(String userName);
}
