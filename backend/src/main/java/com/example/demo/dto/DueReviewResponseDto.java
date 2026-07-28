package com.example.demo.dto;

import java.time.LocalDateTime;

public class DueReviewResponseDto {

    private Long cardId;
    private String frontContent;
    private String backContent;
    private LocalDateTime nextReviewDate;
    private String masteryLevel;

    public DueReviewResponseDto() {
    }

    public DueReviewResponseDto(Long cardId, String frontContent, String backContent,
                                 LocalDateTime nextReviewDate, String masteryLevel) {
        this.cardId = cardId;
        this.frontContent = frontContent;
        this.backContent = backContent;
        this.nextReviewDate = nextReviewDate;
        this.masteryLevel = masteryLevel;
    }

    public Long getCardId() {
        return cardId;
    }

    public void setCardId(Long cardId) {
        this.cardId = cardId;
    }

    public String getFrontContent() {
        return frontContent;
    }

    public void setFrontContent(String frontContent) {
        this.frontContent = frontContent;
    }

    public String getBackContent() {
        return backContent;
    }

    public void setBackContent(String backContent) {
        this.backContent = backContent;
    }

    public LocalDateTime getNextReviewDate() {
        return nextReviewDate;
    }

    public void setNextReviewDate(LocalDateTime nextReviewDate) {
        this.nextReviewDate = nextReviewDate;
    }

    public String getMasteryLevel() {
        return masteryLevel;
    }

    public void setMasteryLevel(String masteryLevel) {
        this.masteryLevel = masteryLevel;
    }
}