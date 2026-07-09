package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class StudySession {
    @Id
    private long id;
    @OneToOne
    @JoinColumn(name = "user_id")
    private SystemUser user;
    @OneToOne
    @JoinColumn(name = "deck_id")
    private StudyDeck deck;

    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int totalCardsReviewed;
    private int correctAnswers;
    private int incorrectAnswers;
    private double accuracyRate;
    public enum SessionStatus {
        ACTIVE,
        COMPLETED
    }
    @Enumerated(EnumType.STRING)
    private SessionStatus sessionStatus;
    
}
