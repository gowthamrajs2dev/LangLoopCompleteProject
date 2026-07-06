package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.Size;
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
    @Size(min=1,max=5)
    private int difficultyRating;
    private int reviewCount;
    private LocalDateTime creationTime;
    @ManyToOne
    @JoinColumn(name = "deck")
    private StudyDeck deck;

    @OneToOne(mappedBy = "flashcard")
    private RetentionMetric retentionMetric;
}
