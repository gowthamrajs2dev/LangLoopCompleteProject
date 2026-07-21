package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.FlashcardRequestDto;
import com.example.demo.dto.FlashcardResponseDto;
import com.example.demo.entity.Flashcard;
import com.example.demo.repository.FlashcardRepository;

@Service
public class FlashcardService {

    /* @Id
    Flashcard entity:
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
    
    requst dto
     private String frontContent;
    private String backContent;
    private int orderIndex;
    */
    @Autowired
    FlashcardRepository flashcardRepository;
    public FlashcardResponseDto createFlashCard(Long deckId,FlashcardRequestDto dto){
        Flashcard flashcard=new Flashcard();
        flashcard.setFrontContent(dto.getFrontContent());
        flashcard.setBackContent(dto.getBackContent());
        flashcard.setOrderIndex(dto.getOrderIndex());

        Flashcard result=flashcardRepository.save(flashcard);
        
    }
}
