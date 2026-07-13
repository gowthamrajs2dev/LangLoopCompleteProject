package com.example.demo.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name="system_deck")
public class StudyDeck {
    @Id
    private Long id;
    @NotBlank //" " empty space is not allowed
    private String title;
    private String description;
    @Min(1)
    private int capacity; 
    @NotBlank
    private String mentorName;
    private long ownerId;
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
    @JoinColumn(name = "system_user")
    private SystemUser systemUser;
    @OneToMany(mappedBy = "deck",cascade = CascadeType.ALL)
    private List<Flashcard> flashcards;
    // @ManyToOne
    // @JoinColumn(name = "language_track")
    // private LanguageTrack languageTrack;

    // @OneToOne(mappedBy = "deck")
    // private StudySession studySession;
}
