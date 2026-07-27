package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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
    private double easeFactor=2.5;
    private int intervalDays;
    private LocalDateTime nextReviewDate;
    public enum MasteryLevel{
        NEW,
        LEARNING,
        MASTERED
    }
    @Enumerated(EnumType.STRING)
    private MasteryLevel masteryLevel;

    @ManyToOne
    
    private Long userId;

    private Long card
}
