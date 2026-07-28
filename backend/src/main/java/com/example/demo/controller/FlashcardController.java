package com.example.demo.controller;

import com.example.demo.dto.FlashcardRequestDto;
import com.example.demo.dto.FlashcardResponseDto;
import com.example.demo.service.FlashcardService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/decks/{id}/cards")
public class FlashcardController {

    private final FlashcardService flashcardService;

    public FlashcardController(FlashcardService flashcardService) {
        this.flashcardService = flashcardService;
    }

    @GetMapping
    public ResponseEntity<List<FlashcardResponseDto>> getCardsByDeck(@PathVariable("id") Long deckId) {
        return ResponseEntity.ok(flashcardService.getCardsByDeck(deckId));
    }
    @PostMapping
    @PreAuthorize("hasAnyRole('LINGUIST', 'ADMIN')")
    public ResponseEntity<FlashcardResponseDto> createFlashcard(@PathVariable("id") Long deckId,
                                                                 @Valid @RequestBody FlashcardRequestDto dto) {
        FlashcardResponseDto created = flashcardService.createFlashcard(deckId, dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
}