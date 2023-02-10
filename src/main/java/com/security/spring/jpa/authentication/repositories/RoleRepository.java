package com.security.spring.jpa.authentication.repositories;

import com.security.spring.jpa.authentication.models.CustomRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<CustomRole, Long> {

        @Query("select r from CustomRole r where r.roleName = :roleName")
        Optional<CustomRole> findbyRoleName(@Param("roleName") String roleName);
}
