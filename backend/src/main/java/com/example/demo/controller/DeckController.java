package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.StudyDeck;
import com.example.demo.service.DeckManagementService;

@RestController
@RequestMapping("/api/decks")
public class DeckController {
    private DeckManagementService deckManagementService;
    @GetMapping
    public List<StudyDeck> getAllStudyDecks(){
        return deckManagementService.getAllStudyDecks();
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getStudyDeck(@PathVariable long id){
        deckManagementService.getStudyDeck(id);
    }
}
