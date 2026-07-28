package com.example.demo.dto;

public class FlashcardResponseDto {

    private Long id;
    private String frontContent;
    private String backContent;
    private Integer orderIndex;
    private Long deckId;

    public FlashcardResponseDto() {
    }

    public FlashcardResponseDto(Long id, String frontContent, String backContent,
                                 Integer orderIndex, Long deckId) {
        this.id = id;
        this.frontContent = frontContent;
        this.backContent = backContent;
        this.orderIndex = orderIndex;
        this.deckId = deckId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(Integer orderIndex) {
        this.orderIndex = orderIndex;
    }

    public Long getDeckId() {
        return deckId;
    }

    public void setDeckId(Long deckId) {
        this.deckId = deckId;
    }
}