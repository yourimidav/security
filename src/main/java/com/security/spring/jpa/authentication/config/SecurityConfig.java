package com.security.spring.jpa.authentication.config;

import com.security.spring.jpa.authentication.models.RoleDeMoi;
import com.security.spring.jpa.authentication.models.User;
import com.security.spring.jpa.authentication.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    UserDetailsService userDetailsService;

/*    @Value("${spring.h2.console.path}")
    private String h2ConsolePath;*/

    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.disable())
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(request -> request
                        .requestMatchers("/home/**", "/register/**", "", "/").permitAll()
                        .requestMatchers(/*h2ConsolePath +*/ "/**").authenticated()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/details/**").hasRole("USER")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form.permitAll())
                .logout(logout -> logout.permitAll())
                .userDetailsService(userDetailsService)
                .headers(headers -> headers.frameOptions().sameOrigin());
        return http.build();
    }

    @Bean
    CommandLineRunner commandLineRunner(UserRepository userRepository) {
        return args -> {
            RoleDeMoi rAdmin = new RoleDeMoi("ROLE_ADMIN");
            RoleDeMoi rUser = new RoleDeMoi("ROLE_USER");
            User user = new User("user",
                    passwordEncoder().encode("pass"),
                    List.of(rAdmin, rUser)
            );
            userRepository.save(user);
        };
    }
}
