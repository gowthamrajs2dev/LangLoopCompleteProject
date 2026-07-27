package com.example.demo.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.FlashcardResponseDto;

@RestController
@RequestMapping("/api/decks")
public class FlashcardController {
    
    @GetMapping("/{id}/cards")
    public List<FlashcardResponseDto> getCardsByDeck(Long deckId){

    }
}
