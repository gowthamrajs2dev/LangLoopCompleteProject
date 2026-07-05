package com.example.demo.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.Data;

@Data
@Entity
@Table(name = "system_user")
public class SystemUser {
    @Id
    private long id;
    @Column(unique = true)
    private String username;
    @Column(unique=true)
    @Email
    private String email;
    private String password;
    @Enumerated(EnumType.STRING)
    private UserRole role;
    private String nativeLanguage;
    private String learningLanguage;
    private LocalDateTime creationTime;
    private LocalDateTime modifiedTime;
    @OneToMany(mappedBy = "")
    private List<StudyDeck> decks;

}
