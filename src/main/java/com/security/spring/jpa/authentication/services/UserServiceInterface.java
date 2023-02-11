package com.security.spring.jpa.authentication.services;

import com.security.spring.jpa.authentication.models.User;

import java.util.List;

public interface UserServiceInterface {

    List<User> getAllUser();
    User getByUsername(String username);
    User addUser(User user);

    User getUserById(Long id);
}
