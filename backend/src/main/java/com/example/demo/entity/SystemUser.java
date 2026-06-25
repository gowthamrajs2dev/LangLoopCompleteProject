package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class SystemUser {
    @Id
    private Long id;
    @Column(unique = true)
    private String username;
    @Column(unique = true)
    private String email;
    private String password;
    public enum UserRole{
        LEARNER,LINGUIST,ADMIN
    }
    private UserRole role;
    private String nativeLanguage;
    private 
}
