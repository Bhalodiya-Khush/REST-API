package com.exam.system.smart_exam_system.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {

    @Autowired
    private DataSource dataSource;

    @Bean
    public UserDetailsManager userDetailsManager() {

        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);

        // Use users table
        manager.setUsersByUsernameQuery(
                "SELECT email, password, true FROM users WHERE email=?"
        );

        // Use role column
        manager.setAuthoritiesByUsernameQuery(
                "SELECT email, role FROM users WHERE email=?"
        );

        return manager;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(config -> config

                // Public APIs
                .requestMatchers("/api/users/register").permitAll()

                .requestMatchers("/api/questions/exam/**").hasAnyRole("TEACHER", "STUDENT")
                .requestMatchers("/api/options/question/**").hasAnyRole("TEACHER", "STUDENT")

                // Teacher APIs
                .requestMatchers("/api/exams/teacher/**").hasRole("TEACHER")
                .requestMatchers("/api/questions/**").hasRole("TEACHER")
                .requestMatchers("/api/options/**").hasRole("TEACHER")

                // Student APIs
                .requestMatchers("/api/attempts/**").hasRole("STUDENT")

                // All other requests need authentication
                .anyRequest().authenticated()
        );

        //  Basic Auth
        http.httpBasic(Customizer.withDefaults());

        // Disable CSRF (for REST APIs)
        http.csrf(csrf -> csrf.disable());

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}