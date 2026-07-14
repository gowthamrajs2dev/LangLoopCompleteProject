package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.DeckRequestDto;
import com.example.demo.service.DeckMangementServiceImpl;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/decks")
public class DeckController {
    @Autowired
    DeckMangementServiceImpl deckMangementServiceImpl;

    @GetMapping
    public List<DeckRequestDto> getAllDecks(){
        return deckMangementServiceImpl.getAllDecks();
    }

    @GetMapping("/{id}")
    public DeckRequestDto getDeckById(@PathVariable Long id){
        return deckMangementServiceImpl.getDeckById(id);
    }

    @PostMapping
    public ResponseEntity<String> createDeck(@RequestBody DeckRequestDto dto){
        deckMangementServiceImpl.createDeck(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body("new study deck created successfully!");
    }

    @PutMapping("/{id}")
    public ResponseEntity<DeckRequestDto> updateDeck(@PathVariable Long id,@RequestBody DeckRequestDto dto){
        return 
    }
}
