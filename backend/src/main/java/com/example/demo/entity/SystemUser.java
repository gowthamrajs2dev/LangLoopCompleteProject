package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;

@Entity
public class SystemUser {
    @Id
    private long id;
    @Column(unique = true)
    private String username;
    @Column(unique=true)
    @Email
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserRole role;
    private String nativeLanguage;
    private String learningLanguage;
    private LocalDateTime creationDateTime;
    private LocalDateTime 
}
