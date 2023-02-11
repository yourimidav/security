package com.security.spring.jpa.authentication.repositories;

import com.security.spring.jpa.authentication.models.RoleDeMoi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleDeMoi, Long> {

        @Query("select r from RoleDeMoi r where r.RoleNom = :RoleNom")
        Optional<RoleDeMoi> findbyRoleNom(@Param("RoleNom") String RoleNom);
}
