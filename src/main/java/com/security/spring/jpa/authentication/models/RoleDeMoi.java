package com.security.spring.jpa.authentication.models;

import jakarta.persistence.*;

@Entity
@Table(name = "roles")
public class RoleDeMoi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String RoleNom;

    public RoleDeMoi() {
    }

    public RoleDeMoi(String RoleNom) {
        this.RoleNom = RoleNom;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleNom() {
        return RoleNom;
    }

    public void setRoleNom(String roleNom) {
        this.RoleNom = roleNom;
    }
}
