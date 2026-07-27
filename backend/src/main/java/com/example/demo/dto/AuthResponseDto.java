package com.example.demo.dto;

import com.example.demo.entity.UserRole;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class AuthResponseDto {
    private String token;
    private String username;
    private UserRole role;
    public AuthResponseDto(String token,String username,String role){
        this.token=token;
        this.role=;
        this.username=username;
    }
}
