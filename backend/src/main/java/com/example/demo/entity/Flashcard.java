package com.example.demo.entity;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="flashcard")
public class Flashcard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String frontContent;
    private String backContent;
    private String pronunciationGuide;
    private String exampleSentence;
    private int orderIndex;
    // @Size(min=1,max=5)
    // private int difficultyRating;
    // private int reviewCount;
    // private LocalDateTime creationTime;
    @ManyToOne
    @JoinColumn(name = "deck_id")
    private StudyDeck deck;

    // @OneToOne(mappedBy = "flashcard")
    // private RetentionMetric retentionMetric;
}
