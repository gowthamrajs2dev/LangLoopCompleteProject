package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class RetentionMetric {
    @Id
    private long id;
    private SystemUser user;
    private Flashcard flashcard;
    private double easeFactor;
    private int repetitionCount;
    private int intervalDays;
    private LocalDateTime nextReviewDate;
    private LocalDateTime lastReviewedAt;
}
