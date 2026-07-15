package com.example.demo.entity;

import java.util.List;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="system_deck")
public class StudyDeck {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank //" " empty space is not allowed
    private String title;
    private String description;
    @Positive
    private int capacity; 
    @NotBlank 
    private String mentorName;
    // public enum DeskVisibility {
    //     PUBLIC,
    //     PRIVATE
    // }
    // @Enumerated(EnumType.STRING)
    // private DeskVisibility visibility;
    // private int flashcardCount;
    // private LocalDateTime creationTime;
    // private LocalDateTime modifiedTime;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private SystemUser owner;
    @OneToMany(mappedBy = "deck",cascade = CascadeType.ALL)
    private List<Flashcard> flashcards;
    // @ManyToOne
    // @JoinColumn(name = "language_track")
    // private LanguageTrack languageTrack;

    // @OneToOne(mappedBy = "deck")
    // private StudySession studySession;
}
