package com.example.demo.dto;

import jakarta.persistence.Enumerated;
import lombok.Data;

@Data
public class RegisterRequestDto {
    private String username;
    private String password;
    public enum Role{
        
    }
    @Enumerated
    private Role role;
}
