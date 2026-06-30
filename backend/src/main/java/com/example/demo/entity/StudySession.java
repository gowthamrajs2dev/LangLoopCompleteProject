package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class StudySession {
    @Id
    private long id;
    // private SystemUser
    // private studyDeck
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private int reviewCount;
}
