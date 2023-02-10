package com.security.spring.jpa.authentication.controllers;

import com.security.spring.jpa.authentication.models.CustomRole;
import com.security.spring.jpa.authentication.models.CustomUser;
import com.security.spring.jpa.authentication.services.RoleServiceInterface;
import com.security.spring.jpa.authentication.services.UserService;
import com.security.spring.jpa.authentication.services.UserServiceInterface;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class HomeController {

    private UserServiceInterface userServiceInterface;
    private RoleServiceInterface roleServiceInterface;
    private PasswordEncoder passwordEncoder;

    public HomeController(UserServiceInterface userServiceInterface, RoleServiceInterface roleServiceInterface, PasswordEncoder passwordEncoder) {
        this.userServiceInterface = userServiceInterface;
        this.roleServiceInterface = roleServiceInterface;
        this.passwordEncoder = passwordEncoder;
    }

    @Value("${spring.h2.console.path}")
    private String h2ConsolePath;

    @GetMapping({"", "/"})
    public void redirectToHome(HttpServletResponse response) throws IOException {
        response.sendRedirect("/home");
    }

    @GetMapping("/home")
    public Map<String, String> homePage() {
        Map<String, String> routes = new HashMap<>();
        routes.put("admin", "/admin");
        routes.put("details", "/details");
        routes.put("h2", h2ConsolePath);
        return routes;
    }

    @GetMapping("/admin")
    public String adminPage() {
        return "Vous êtes sur la page admin...";
    }

    @GetMapping("/details")
    public String detailsPage() {
        return "Vous vous trouvez sur la page details...";
    }

    @PostMapping("/register")
    public CustomUser registerUser(@RequestBody CustomUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        CustomRole roleUser = roleServiceInterface.getByRoleName("ROLE_USER");
        user.setRoles(List.of(roleUser));
        return userServiceInterface.addUser(user);
    }
}
