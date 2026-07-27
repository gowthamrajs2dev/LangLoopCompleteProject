package com.example.demo.dto;

import com.example.demo.entity.UserRole;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class AuthResponseDto {
    private String token;
    private String username;
    private UserRole role;
    public AuthResponseDto()
}
