package com.shopmarket.services.impl;

import com.shopmarket.models.Address;
import com.shopmarket.repositories.AddressRepository;
import com.shopmarket.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    AddressRepository addressRepository;

    @Override
    public Address save(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public List<Address> findByUserName(String userName) {
        return null;
    }
}
