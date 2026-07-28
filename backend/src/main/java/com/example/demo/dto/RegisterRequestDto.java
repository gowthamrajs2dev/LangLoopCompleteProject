package com.example.demo.dto;

import com.example.demo.entity.SystemUser;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class RegisterRequestDto {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotNull
    private SystemUser.Role role;

    public RegisterRequestDto() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public SystemUser.Role getRole() {
        return role;
    }

    public void setRole(SystemUser.Role role) {
        this.role = role;
    }
}