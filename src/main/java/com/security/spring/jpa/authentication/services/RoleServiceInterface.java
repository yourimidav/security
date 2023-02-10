package com.security.spring.jpa.authentication.services;

import com.security.spring.jpa.authentication.models.CustomRole;

public interface RoleServiceInterface {
    CustomRole getByRoleName(String roleName);
}
