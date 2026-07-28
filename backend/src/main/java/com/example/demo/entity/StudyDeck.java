package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "study_decks")
public class StudyDeck {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title cannot be blank")
    @Column(nullable = false)
    private String title;

    @Column(length = 1000)
    private String description;

    @Positive(message = "Capacity must be a positive integer")
    @Column(nullable = false)
    private Integer capacity;

    @NotBlank(message = "Mentor name cannot be blank")
    @Column(name = "mentor_name", nullable = false)
    private String mentorName;

    // Direct association with the creator (LINGUIST). Kept as a raw FK id
    // rather than a full @ManyToOne relationship since the service layer
    // only ever needs the owning user's id, not the full SystemUser graph.
    @Column(name = "owner_id", nullable = false)
    private Long ownerId;

    @OneToMany(mappedBy = "deck", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Flashcard> flashcards = new ArrayList<>();

    public StudyDeck() {
    }

    public StudyDeck(String title, String description, Integer capacity, String mentorName, Long ownerId) {
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.mentorName = mentorName;
        this.ownerId = ownerId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getMentorName() {
        return mentorName;
    }

    public void setMentorName(String mentorName) {
        this.mentorName = mentorName;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public List<Flashcard> getFlashcards() {
        return flashcards;
    }

    public void setFlashcards(List<Flashcard> flashcards) {
        this.flashcards = flashcards;
    }
}