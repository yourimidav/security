package com.security.spring.jpa.authentication.services;

import com.security.spring.jpa.authentication.models.RoleDeMoi;
import com.security.spring.jpa.authentication.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserService userService;

    public CustomUserDetailsService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getByUsername(username);
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), rolesToAuthority(user.getRoles()));
    }

    private List<GrantedAuthority> rolesToAuthority(List<RoleDeMoi> roleDeMois) {
        return roleDeMois.stream().map(RoleDeMoi::getRoleNom).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }
}
