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
import com.example.demo.service.DeckMangementServiceImpl;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/api/decks")
public class DeckController {
    @Autowired
    DeckMangementServiceImpl deckMangementServiceImpl;

    @GetMapping
    public List<DeckResponseDto> getAllDecks(){
        return deckMangementServiceImpl.getAllDecks();
    }

    @GetMapping("/{id}")
    public DeckResponseDto getDeckById(@PathVariable Long id){
        return deckMangementServiceImpl.getDeckById(id);
    }

    @PostMapping
    public ResponseEntity<DeckResponseDto> createDeck(@Valid @RequestBody DeckRequestDto dto){
        DeckResponseDto created=deckMangementServiceImpl.createDeck(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DeckResponseDto> updateDeck(@PathVariable Long id,@Valid @RequestBody DeckRequestDto dto){
        DeckResponseDto updated=deckMangementServiceImpl.updateDeck(id, dto);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteDeck(@PathVariable Long id){
        deckMangementServiceImpl.deleteDeck(id);
        return ResponseEntity.ok("Study deck with id: "+id+" deleted successfully!");
    }
}
