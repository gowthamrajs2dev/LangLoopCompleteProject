package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class RetentionMetric {
    @Id
    private long id;
    // @OneToOne
    // @JoinColumn(name = "user_id")
    // private SystemUser user;
    // @OneToOne
    // @JoinColumn(name = "flashcard_id")
    // private Flashcard flashcard;
    private double easeFactor;
    // private int repetitionCount;
    private int intervalDays;
    private LocalDateTime nextReviewDate;
    // private LocalDateTime lastReviewedAt;
}
