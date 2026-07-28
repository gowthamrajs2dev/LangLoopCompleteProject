package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "flashcard")
public class Flashcard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Front content cannot be blank")
    @Column(name = "front_content", nullable = false, length = 500)
    private String frontContent;

    @NotBlank(message = "Back content cannot be blank")
    @Column(name = "back_content", nullable = false, length = 500)
    private String backContent;

    @NotNull(message = "Order index is required for sequencing")
    @Column(name = "order_index", nullable = false)
    private Integer orderIndex;

    // Each card must be linked to a valid StudyDeck (Many-to-One).
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "deck_id", nullable = false)
    private StudyDeck deck;

    public Flashcard() {
    }

    public Flashcard(String frontContent, String backContent, Integer orderIndex, StudyDeck deck) {
        this.frontContent = frontContent;
        this.backContent = backContent;
        this.orderIndex = orderIndex;
        this.deck = deck;
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

    public StudyDeck getDeck() {
        return deck;
    }

    public void setDeck(StudyDeck deck) {
        this.deck = deck;
    }

    // Convenience accessor matching the SRS field name "deckId" without
    // exposing the full StudyDeck graph where only the id is needed.
    @Transient
    public Long getDeckId() {
        return deck != null ? deck.getId() : null;
    }
}