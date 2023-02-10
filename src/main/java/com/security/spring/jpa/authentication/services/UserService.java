package com.security.spring.jpa.authentication.services;

import com.security.spring.jpa.authentication.models.CustomUser;
import com.security.spring.jpa.authentication.repositories.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserServiceInterface {

    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public CustomUser getByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException(String.format("User %s not found!", username))
//                new UsernameNotFoundException(String.format("User " + username + " not found!"))
        );
    }

    @Override
    public CustomUser addUser(CustomUser user) {
        return userRepository.save(user);
    }
}
