package com.security.spring.jpa.authentication.services;

import com.security.spring.jpa.authentication.models.CustomUser;

public interface UserServiceInterface {

    CustomUser getByUsername(String username);
    CustomUser addUser(CustomUser user);
}
