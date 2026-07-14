package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.DeckRequestDto;
import com.example.demo.service.DeckMangementServiceImpl;

@RestController
@RequestMapping("/api")
public class DeckController {
    @Autowired
    DeckMangementServiceImpl deckMangementServiceImpl;

    @GetMapping("/decks")
    public List<DeckRequestDto> getAllDecks(){
        return deckMangementServiceImpl.getAllDecks();
    }

    @GetMapping("/decks/{id}")
    public DeckRequestDto getDeckById(@PathVariable Long id){
        return deckMangementServiceImpl.getDeckById(id);
    }

    public ResponseEntity<String> createDeck()
}
