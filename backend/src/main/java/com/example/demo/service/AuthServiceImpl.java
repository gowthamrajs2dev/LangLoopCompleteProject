package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dto.AuthResponseDto;
import com.example.demo.dto.LoginRequestDto;
import com.example.demo.dto.RegisterRequestDto;
import com.example.demo.entity.SystemUser;
import com.example.demo.exception.UnauthorizedAccessException;
import com.example.demo.repository.SystemUserRepository;
import com.example.demo.security.JwtUtil;

import jakarta.transaction.Transactional;

@Service
public class AuthServiceImpl implements AuthService{

    @Autowired
    private final SystemUserRepository systemUserRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private final JwtUtil jwtUtil;

    @Override
    @Transactional
    public AuthResponseDto register(RegisterRequestDto dto) {
        if (systemUserRepository.existsByUsername(dto.getUsername())) {
            throw new UnauthorizedAccessException("Username '" + dto.getUsername() + "' is already taken");
        }
 
        SystemUser user = new SystemUser();
        user.setUsername(dto.getUsername());
        // BCrypt-encode before persistence, per SRS implementation requirements.
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setRole(dto.getRole());
 
        SystemUser saved = systemUserRepository.save(user);
        String token = jwtUtil.generateToken(saved.getUsername());
 
        return new AuthResponseDto(token, saved.getUsername(), saved.getRole().name());
    }
 
    @Override
    public AuthResponseDto login(LoginRequestDto dto) {
        SystemUser user = systemUserRepository.findByUsername(dto.getUsername())
                .orElseThrow(() -> new UnauthorizedAccessException("Invalid username or password"));
 
        if (!passwordEncoder.matches(dto.getPassword(), user.getPassword())) {
            throw new UnauthorizedAccessException("Invalid username or password");
        }
 
        String token = jwtUtil.generateToken(user.getUsername());
        return new AuthResponseDto(token, user.getUsername(), user.getRole().name());
    }

    
}
