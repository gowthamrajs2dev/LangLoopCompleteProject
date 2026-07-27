package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.DeckRequestDto;
import com.example.demo.dto.DeckResponseDto;
import com.example.demo.service.DeckManagementService;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/api/decks")
public class DeckController {
    @Autowired
    DeckManagementService deckManagementService; //lose coupling

    @GetMapping
    public List<DeckResponseDto> getAllDecks(){
        return deckManagementService.getAllDecks();
    }

    @GetMapping("/{id}")
    public DeckResponseDto getDeckById(@PathVariable Long id){
        return deckManagementService.getDeckById(id);
    }

    @PostMapping //admin or linguist
    public ResponseEntity<DeckResponseDto> createDeck(@Valid @RequestBody DeckRequestDto dto){
        DeckResponseDto created=deckManagementService.createDeck(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}") //Admin or linguist
    public ResponseEntity<DeckResponseDto> updateDeck(@PathVariable Long id,@Valid @RequestBody DeckRequestDto dto){
        DeckResponseDto updated=deckManagementService.updateDeck(id, dto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(updated);
    }

    @DeleteMapping("/{id}") //only ADMIN
    public ResponseEntity<String> deleteDeck(@PathVariable Long id){
        deckManagementService.deleteDeck(id);
        return ResponseEntity.ok("Study deck with id: "+id+" deleted successfully!");
    }
}
