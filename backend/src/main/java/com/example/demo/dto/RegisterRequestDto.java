package com.example.demo.dto;

import com.example.demo.entity.UserRole;

import jakarta.persistence.EnumType;
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
   
    @Enumerated(EnumType.STRING)
    private UserRole role;
}
