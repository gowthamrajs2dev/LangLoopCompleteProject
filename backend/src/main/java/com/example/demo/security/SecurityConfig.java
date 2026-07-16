package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http){
        http.csrf().disable()
        .authorizeHttpRequests(
            auth->auth
            .requestMatchers(
                "/login","/register","/post"
            ).permitAll()
            .requestMatchers(
                "/swagger-ui/**","swagger-ui.html","/v3/api-docs/**"
            ).permitAll()
            .requestMatchers("/delete/**").hasRole("LINGUIST")
            .anyRequest().authenticated()
        )
    }
}
