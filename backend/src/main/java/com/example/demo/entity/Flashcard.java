package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name="flashcard")
public class Flashcard {
    @Id
    @Ge
    private long id;
    private String frontContent;
    private String backContent;
    private String pronunciationGuide;
    private String exampleSentence;
    private int orderIndex;
    // @Size(min=1,max=5)
    // private int difficultyRating;
    // private int reviewCount;
    // private LocalDateTime creationTime;
    private Long deckId;
    @ManyToOne
    @JoinColumn(name = "deck")
    private StudyDeck deck;

    // @OneToOne(mappedBy = "flashcard")
    // private RetentionMetric retentionMetric;
}
