package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="retention_metric")
public class RetentionMetric {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // @OneToOne
    // @JoinColumn(name = "user_id")
    // private SystemUser user;
    // @OneToOne
    // @JoinColumn(name = "flashcard_id")
    // private Flashcard flashcard;
    private double easeFactor=2.5;
    // private int repetitionCount;
    private int intervalDays;
    private LocalDateTime nextReviewDate;
    // private LocalDateTime lastReviewedAt;

    public enum MasteryLevel{
        NEW,
        LEARNING,
        MASTERED
    }
    @Enumerated
    private MasteryLevel masteryLevel;
}
