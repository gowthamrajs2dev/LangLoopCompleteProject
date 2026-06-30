package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class RetentionMetric {
    @Id
    private long id;
    // private SystemUser user;
    // private Flashcard flashcard;
    private double easeFactor;
    
}
