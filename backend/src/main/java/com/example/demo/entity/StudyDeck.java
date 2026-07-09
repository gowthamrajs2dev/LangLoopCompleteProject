package com.example.demo.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Data
@Entity
public class StudyDeck {
    @Id
    private long id;
    private String title;
    private String description;
    public enum DeskVisibility {
        PUBLIC,
        PRIVATE
    }
    @Enumerated(EnumType.STRING)
    private DeskVisibility visibility;
    private int flashcardCount;
    private LocalDateTime creationTime;
    private LocalDateTime modifiedTime;
    @ManyToOne
    @JoinColumn(name = "system_user")
    private SystemUser systemUser;
    @OneToMany(mappedBy = "deck",cascade = CascadeType.ALL)
    private List<Flashcard> flashcards;
    @ManyToOne
    @JoinColumn(name = "language_track")
    private LanguageTrack languageTrack;

    @OneToOne(mappedBy = "deck")
    private StudySession studySession;
}
