package com.security.spring.jpa.authentication.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class CustomUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String password;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id")
    )
    private List<CustomRole> roles;

    public CustomUser() {
    }

    public CustomUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public CustomUser(String username, String password, List<CustomRole> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<CustomRole> getRoles() {
        return roles;
    }

    public void setRoles(List<CustomRole> roles) {
        this.roles = roles;
    }
}
