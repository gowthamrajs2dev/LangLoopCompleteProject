package com.example.demo.service;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.DeckRequestDto;
import com.example.demo.dto.DeckResponseDto;




@Service
public interface DeckManagementService {
    public List<DeckResponseDto> getAllDecks();
    public DeckResponseDto getDeckById(Long id);
    public DeckResponseDto createDeck(DeckRequestDto dto);
    public String deleteDeck(Long id);
    public DeckResponseDto updateDeck(Long id,DeckRequestDto dto) ;
} 