package com.example.demo.service;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.FlashcardRequestDto;
import com.example.demo.dto.FlashcardResponseDto;
import com.example.demo.entity.Flashcard;
import com.example.demo.entity.StudyDeck;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.FlashcardRepository;
import com.example.demo.repository.StudyDeckRepository;

import jakarta.transaction.Transactional;

@Service
public class FlashcardServicImpl implements FlashcardService{
    
    @Autowired
    private final FlashcardRepository flashcardRepository;
    @Autowired
    private  StudyDeckRepository studyDeckRepository;
 
    
 
    @Override
    @Transactional
    public FlashcardResponseDto createFlashcard(Long deckId, FlashcardRequestDto dto) {
        StudyDeck deck = studyDeckRepository.findById(deckId)
                .orElseThrow(() -> new ResourceNotFoundException("StudyDeck not found with id: " + deckId));
 
        Flashcard flashcard = new Flashcard();
        flashcard.setFrontContent(dto.getFrontContent());
        flashcard.setBackContent(dto.getBackContent());
        flashcard.setDeck(deck);
 
        // Maintains sequential ordering: assign the next index automatically
        // when the caller doesn't supply one.
        Integer orderIndex = dto.getOrderIndex();
        if (orderIndex == null) {
            orderIndex = (int) flashcardRepository.countByDeckId(deckId) + 1;
        }
        flashcard.setOrderIndex(orderIndex);
 
        return toResponseDto(flashcardRepository.save(flashcard));
    }
 
    @Override
    public List<FlashcardResponseDto> getCardsByDeck(Long deckId) {
        return flashcardRepository.findByDeckIdOrderByOrderIndexAsc(deckId).stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }
 
    private FlashcardResponseDto toResponseDto(Flashcard flashcard) {
        return new FlashcardResponseDto(
                flashcard.getId(),
                flashcard.getFrontContent(),
                flashcard.getBackContent(),
                flashcard.getOrderIndex(),
                flashcard.getDeckId()
        );
    }
}
