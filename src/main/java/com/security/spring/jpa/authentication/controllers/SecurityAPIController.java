package com.security.spring.jpa.authentication.controllers;

import com.security.spring.jpa.authentication.models.RoleDeMoi;
import com.security.spring.jpa.authentication.models.User;
import com.security.spring.jpa.authentication.services.RoleServiceInterface;
import com.security.spring.jpa.authentication.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api")
public class SecurityAPIController {


    private UserService userService;
    private RoleServiceInterface roleServiceInterface;
    private PasswordEncoder passwordEncoder;

    public SecurityAPIController(UserService userService, RoleServiceInterface roleServiceInterface, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleServiceInterface = roleServiceInterface;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/users")
    public List<User> AllUserById(@RequestParam(required = false) Long id) {
        List<User> listOfUser = new ArrayList<>();
        if (id == null) return userService.getAllUser();
        else return List.of(userService.getUserById(id));
    }

    @GetMapping("/meendev")
    public UserDetails Me(HttpServletRequest request) {
        return (UserDetails) request.getUserPrincipal();
    }

    @GetMapping("/me")
    @ResponseBody
    public String currentUserName(Principal principal) {
        return principal.getName();
    }

    @PostMapping("/add-user")
    public User addUser(@RequestBody User user) {
        System.out.println(user);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        RoleDeMoi roleUser = roleServiceInterface.getByRoleNom("ROLE_USER");
        user.setRoles(List.of(roleUser));
        return userService.addUser(user);
    }


    @PostMapping("/change-role")
    public User registerUser(@RequestBody Long id) {
        User user =userService.getUserById(id);
        RoleDeMoi rUser = roleServiceInterface.getByRoleNom("ROLE_USER");
        user.setRoles(List.of(rUser));
        return user;
    }
}
