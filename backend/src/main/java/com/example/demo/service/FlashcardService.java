package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.FlashcardRequestDto;
import com.example.demo.dto.FlashcardResponseDto;

public interface FlashcardService {
    FlashcardResponseDto createFlashcard(Long deckId, FlashcardRequestDto dto);
 
    List<FlashcardResponseDto> getCardsByDeck(Long deckId);
    
} 