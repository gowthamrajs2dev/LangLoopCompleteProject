package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
    private String 

}
