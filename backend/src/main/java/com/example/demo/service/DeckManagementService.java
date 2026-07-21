package com.example.demo.service;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.DeckRequestDto;
import com.example.demo.dto.DeckResponseDto;
import com.example.demo.entity.StudyDeck;



@Service
public interface DeckManagementService {
    public static DeckResponseDto convertToDeckResponseDto(StudyDeck studyDeck);
    public List<DeckResponseDto> getAllDecks();
    public DeckResponseDto getDeckById(Long id);

    public DeckResponseDto createDeck(DeckRequestDto dto){

    public String deleteDeck(Long id){
        StudyDeck studyDeck = studyDeckRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("id not found while deletion"));
        studyDeckRepository.delete(studyDeck);
        return "Study deck with id "+id+" is deleted successfully";
        // studyDeckRepository.deleteById(id);
    }

    public DeckResponseDto updateDeck(Long id,DeckRequestDto dto) {
        StudyDeck studyDeck=studyDeckRepository.findById(id)
        .orElseThrow(()->new ResourceNotFoundException("no id found for updating study deck"));
        studyDeck.setTitle(dto.getTitle());
        studyDeck.setCapacity(dto.getCapacity());
        studyDeck.setDescription(dto.getDescription());
        // studyDeck.setMentorName();
        // studyDeck.setOwnerId();
        // studyDeck.setSystemUser();
        StudyDeck saved=studyDeckRepository.save(studyDeck);

        return convertToDeckResponseDto(saved);
    }

    
} 