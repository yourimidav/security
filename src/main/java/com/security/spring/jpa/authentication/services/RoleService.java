package com.security.spring.jpa.authentication.services;

import com.security.spring.jpa.authentication.models.RoleDeMoi;
import com.security.spring.jpa.authentication.repositories.RoleRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class RoleService implements RoleServiceInterface {

    private RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public RoleDeMoi getByRoleNom(String roleNom) throws UsernameNotFoundException {
        return roleRepository.findbyRoleNom(roleNom).orElseThrow(() ->
                new UsernameNotFoundException(String.format("Role %s was not found!", roleNom))
        );
    }
}
