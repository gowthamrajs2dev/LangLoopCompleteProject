package com.example.demo.dto;

import com.example.demo.entity.UserRole;

import lombok.Data;

@Data
public class AuthResponseDto {
    // private Long token;
    private String username;
    private UserRole role;
}
