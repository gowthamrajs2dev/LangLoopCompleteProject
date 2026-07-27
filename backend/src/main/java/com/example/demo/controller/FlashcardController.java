package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.FlashcardRequestDto;
import com.example.demo.dto.FlashcardResponseDto;
import com.example.demo.service.FlashcardService;

@RestController
@RequestMapping("/api/decks")
public class FlashcardController {
    @Autowired
    private FlashcardService flashcardService;
    @GetMapping("/{id}/cards")
    public List<FlashcardResponseDto> getCardsByDeck(@PathVariable Long id){
        return flashcardService.getCardsByDeck(id);
    }

    @PostMapping("/{id}/cards")
    public FlashcardResponseDto createFlashcard(@PathVariable Long id,FlashcardRequestDto dto){
        return flashcardService.createFlashCard(id, dto);
    }
}
