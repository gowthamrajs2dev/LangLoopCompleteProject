package com.example.demo.service;

import org.springframework.stereotype.Service;

import com.example.demo.dto.FlashcardRequestDto;
import com.example.demo.dto.FlashcardResponseDto;

@Service
public class FlashcardService {

    /* @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String frontContent;
    private String backContent;
    private String pronunciationGuide;
    private String exampleSentence;
    private int orderIndex;
    @ManyToOne
    @JoinColumn(name = "deck_id")
    private StudyDeck deck; 
    
    
     private String frontContent;
    private String backContent;
    private int orderIndex;
    */
    public FlashcardResponseDto createFlashCard(Long deckId,FlashcardRequestDto dto){

    }
}
