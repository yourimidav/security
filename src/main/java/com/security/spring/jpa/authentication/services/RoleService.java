package com.security.spring.jpa.authentication.services;

import com.security.spring.jpa.authentication.models.CustomRole;
import com.security.spring.jpa.authentication.repositories.RoleRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class RoleService implements RoleServiceInterface {

    private RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public CustomRole getByRoleName(String roleName) throws UsernameNotFoundException {
        return roleRepository.findbyRoleName(roleName).orElseThrow(() ->
                new UsernameNotFoundException(String.format("Role %s was not found!", roleName))
        );
    }
}
