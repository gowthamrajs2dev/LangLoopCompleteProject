package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Flashcard {
    @Id
    private long id;
    private String frontText;
    private String backText;
    private String pronunciationGuide;
    private String exampleSentence;
    @
    private int difficultyRating;
}
