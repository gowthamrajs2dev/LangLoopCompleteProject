package com.example.demo.dto;

import java.time.LocalDateTime;

public class StudySessionResponseDto {

    private Long id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Integer score;
    private Long userId;
    private Long deckId;

    public StudySessionResponseDto() {
    }

    public StudySessionResponseDto(Long id, LocalDateTime startTime, LocalDateTime endTime,
                                    Integer score, Long userId, Long deckId) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.score = score;
        this.userId = userId;
        this.deckId = deckId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getDeckId() {
        return deckId;
    }

    public void setDeckId(Long deckId) {
        this.deckId = deckId;
    }
}