package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class SystemUser {
    @Id
    private long id;
    private String username;
}
