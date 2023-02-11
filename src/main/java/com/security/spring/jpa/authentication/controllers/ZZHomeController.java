package com.security.spring.jpa.authentication.controllers;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class ZZHomeController {
/*
    private ZZUserServiceInterface userServiceInterface;
    private ZZRoleServiceInterface roleServiceInterface;
    private PasswordEncoder passwordEncoder;

    public ZZHomeController(ZZUserServiceInterface userServiceInterface, ZZRoleServiceInterface roleServiceInterface, PasswordEncoder passwordEncoder) {
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
    public User registerUser(@RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role roleUser = roleServiceInterface.getByRoleName("ROLE_USER");
        user.setRoles(List.of(roleUser));
        return userServiceInterface.addUser(user);
    }*/
}
