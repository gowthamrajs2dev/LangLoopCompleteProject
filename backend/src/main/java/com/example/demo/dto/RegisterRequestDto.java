package com.example.demo.dto;

import com.example.demo.entity.UserRole;

import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RegisterRequestDto {
    private String username;
    private String password;
    public enum Role{

    }
    @Enumerated
    private UserRole role;
}
