package com.example.demo.service;

import com.example.demo.dto.DeckRequestDto;
import com.example.demo.dto.DeckResponseDto;

import java.util.List;

public interface DeckManagementService {

    List<DeckResponseDto> getAllDecks();

    DeckResponseDto getDeckById(Long id);

    DeckResponseDto createDeck(DeckRequestDto dto);

    DeckResponseDto updateDeck(Long id, DeckRequestDto dto);

    void deleteDeck(Long id);
}