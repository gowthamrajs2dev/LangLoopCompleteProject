package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.repository.SystemUserRepository;
import com.example.demo.security.JwtUtil;

@Service
public class AuthServiceImpl implements AuthService{

    @Autowired
    private final SystemUserRepository systemUserRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final JwtUtil jwtUtil;

    
}
