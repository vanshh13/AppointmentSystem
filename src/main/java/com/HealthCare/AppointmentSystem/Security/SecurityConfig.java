package com.HealthCare.AppointmentSystem.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.password.NoOpPasswordEncoder; // Import this
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        jdbcUserDetailsManager.setUsersByUsernameQuery(
                "SELECT username, password, enabled FROM users WHERE username=?");

        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
                "SELECT username, authority FROM authorities WHERE username=?");

        return jdbcUserDetailsManager;
    }

    // CHANGE THIS BEAN TO USE NoOpPasswordEncoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        // !!! WARNING: This is NOT secure for production environments !!!
        // It provides no password hashing and should ONLY be used for development/testing
        // where security is not a concern.
        return NoOpPasswordEncoder.getInstance();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers(HttpMethod.GET,
                                "/Appointment", "/Appointment/**",
                                "/Doctor", "/Doctor/**",
                                "/Patient", "/Patient/**",
                                "/Department", "/Department/**")
                        .hasAnyRole("Doctor", "PATIENT", "ADMIN")

                        .requestMatchers(HttpMethod.POST, "/Department/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/Department/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/Department/**").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.POST, "/Doctor/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/Doctor/**").hasAnyRole("Doctor", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/Doctor/**").hasRole("ADMIN")

                        .requestMatchers(HttpMethod.POST, "/Patient/**").hasRole("PATIENT")
                        .requestMatchers(HttpMethod.PUT, "/Patient/**").hasRole("PATIENT")
                        .requestMatchers(HttpMethod.DELETE, "/Patient/**").hasRole("Doctor")

                        .requestMatchers(HttpMethod.POST, "/Appointment/**").hasRole("PATIENT")
                        .requestMatchers(HttpMethod.PUT, "/Appointment/**").hasRole("PATIENT")
                        .requestMatchers(HttpMethod.DELETE, "/Appointment/**").hasAnyRole("Doctor", "PATIENT")

                        .anyRequest().authenticated()
        );

        http.httpBasic(Customizer.withDefaults());
        http.csrf(csrf -> csrf.disable());

        return http.build();
    }
}