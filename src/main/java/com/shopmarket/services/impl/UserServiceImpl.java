package com.shopmarket.services.impl;

import com.shopmarket.exceptions.UserAlreadyExistException;
import com.shopmarket.models.User;
import com.shopmarket.models.enams.Roles;
import com.shopmarket.repositories.AddressRepository;
import com.shopmarket.repositories.RoleRepository;
import com.shopmarket.repositories.UserRepository;
import com.shopmarket.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User save(final User user) {

        if (findUserByEmail(user.getEmail()).isPresent()) {
            throw new UserAlreadyExistException("There is an account with that email address: " + user.getEmail());
        }


        final User newUser = new User();
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setDateCreated(new Date());
        newUser.setEmail(user.getEmail());
        newUser.setGender(user.getGender());
        newUser.setBirthDate(user.getBirthDate());
        newUser.setEnabled(user.isEnabled());
//        newUser.setPhoneNumber(user.getPhoneNumber());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        newUser.setRoles(roleRepository.findByName(Roles.USER.toString()));

        System.out.println("service = " + newUser.getPassword() + "; " + newUser.getRoles().toString());
        System.out.println("service = " + newUser.getPasswordConfirm() + "; " + newUser.getRoles().toString());
        return userRepository.save(newUser);
    }
    @Override
    public User update(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }


    @Override
    public Optional<User> findUserByEmail(String email) {
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

    @Override
    public void saveRegisteredUser(User user) {

    }

}
