package com.example.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import com.example.demo.security.JwtAuthenticationFilter;

@Configuration
public class SecurityConfig {
    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
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
        );
        return http.build();
    }
}
