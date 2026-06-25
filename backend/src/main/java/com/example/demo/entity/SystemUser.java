package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class SystemUser {
    @Id
    private Long id;
    @Column(unique = )
    private String username;
}
