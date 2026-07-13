package com.example.demo.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "system_user")
public class SystemUser {
    @Id
    private Long id;
    @Column(unique = true)
    @NotNull
    private String username;
    @Column(unique=true)
    // @Email
    // private String email;
    private String password;
    
    @Enumerated(EnumType.STRING)
    private UserRole role;
    // private String nativeLanguage;
    // private String learningLanguage;
    // private LocalDateTime creationTime;
    // private LocalDateTime modifiedTime;
    @OneToMany(mappedBy = "systemUser")
    private List<StudyDeck> decks;

    // @OneToOne(mappedBy = "user")
    // private StudySession studySession;

    // @OneToOne(mappedBy = "user")
    // private RetentionMetric retentionMetric;
}
