package com.shopmarket.security;

import com.shopmarket.models.User;
import com.shopmarket.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(final String email)  {
//            User user = userRepository.findByEmail(email)
//                    .orElseThrow(()->new UsernameNotFoundException("No user found with username: " + email));
//            return new MyUserPrincipal(user);

        try {
            if (!userRepository.findByEmail(email).isPresent()){
                throw new UsernameNotFoundException("No user found with username: " + email);
            }
            final User user = userRepository.findByEmail(email).get();
            return new MyUserPrincipal(user);

        } catch (final Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }


}
