package com.security.spring.jpa.authentication.services;

import com.security.spring.jpa.authentication.models.RoleDeMoi;

public interface RoleServiceInterface {
    RoleDeMoi getByRoleNom(String roleNom);
}
