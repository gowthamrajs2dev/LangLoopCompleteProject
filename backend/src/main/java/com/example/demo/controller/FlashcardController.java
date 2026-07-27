package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.FlashcardRequestDto;
import com.example.demo.dto.FlashcardResponseDto;
import com.example.demo.service.FlashcardService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/decks")
public class FlashcardController {
    @Autowired
    private FlashcardService flashcardService;
    
     @GetMapping
    public ResponseEntity<List<FlashcardResponseDto>> getCardsByDeck(@PathVariable("id") Long deckId) {
        return ResponseEntity.ok(flashcardService.getCardsByDeck(deckId));
    }
    @PostMapping
    @PreAuthorize("hasAnyRole('LINGUIST', 'ADMIN')")
    public ResponseEntity<FlashcardResponseDto> createFlashcard(@PathVariable("id") Long deckId, @Valid @RequestBody FlashcardRequestDto dto) {
        FlashcardResponseDto created = flashcardService.createFlashcard(deckId, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
}
