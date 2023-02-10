package com.security.spring.jpa.authentication.services;

import com.security.spring.jpa.authentication.models.CustomRole;
import com.security.spring.jpa.authentication.models.CustomUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
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
        CustomUser user = userService.getByUsername(username);
        return new User(user.getUsername(), user.getPassword(), rolesToAuthority(user.getRoles()));
    }

    private List<GrantedAuthority> rolesToAuthority(List<CustomRole> roles) {
/*        List<GrantedAuthority> authorities = new ArrayList<>();
        for (CustomRole role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
        }
        return authorities;*/
        /*return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRoleName())).collect(Collectors.toList());*/
        return roles.stream().map(CustomRole::getRoleName).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }
}
