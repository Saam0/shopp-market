package com.shopmarket.services;

import com.shopmarket.models.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {
    User save(User user);

    User update(User user);

    Optional<User> findById(Long id);

    Optional<User> findUserByEmail(String email);

    //    User findUserByEmail(String email);
    Optional<User> findByLogin(String email);

    boolean deleteUser(Long id);

    void saveRegisteredUser(User user);

}
