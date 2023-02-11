package com.security.spring.jpa.authentication.controllers;

import com.security.spring.jpa.authentication.models.User;
import com.security.spring.jpa.authentication.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api")
public class SecurityAPIController {


    private UserService userService;

    public SecurityAPIController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> AllUserById(@RequestParam(required=false) Long id) {
        List<User> listOfUser=new ArrayList<>();
        if (id==null)return userService.getAllUser();
        else return List.of(userService.getUserById(id));
    }
}
