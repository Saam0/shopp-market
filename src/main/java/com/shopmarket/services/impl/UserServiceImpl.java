/*
package com.shopmarket.services.impl;

import com.shopmarket.models.Role;
import com.shopmarket.models.User;
import com.shopmarket.models.enams.Roles;
import com.shopmarket.repositories.AddressRepository;
import com.shopmarket.repositories.RoleRepository;
import com.shopmarket.repositories.UserRepository;
import com.shopmarket.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;
import java.util.Optional;

public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    AddressRepository addressRepository;


    @Override
    public User save(User user) {

        if (findByEmail(user.getEmail()).isPresent()){
//            throw new UserAlreadyExistException("There is an account with that email address: " + user.getEmail());
        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setEnabled(true);
        user.setDateCreated(new Date());
        user.setRoles(roleRepository.findByRoleName(Roles.ROLE_USER.toString()));
        return userRepository.save(user);
    }

//    private boolean emailExists(String email){
//        return userRepository.findByEmail(email)!=null;
//    }


    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> findByLogin(String email) {
        return Optional.empty();
    }

    @Override
    public boolean deleteUser(Long id) {
        return false;
    }
}
*/
