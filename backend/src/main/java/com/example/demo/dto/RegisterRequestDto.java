package com.example.demo.dto;

import com.example.demo.entity.UserRole;

import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class RegisterRequestDto {
    private String username;
    private String password;
    public enum Role{

    }
    @Enumerated
    private UserRole role;
}
