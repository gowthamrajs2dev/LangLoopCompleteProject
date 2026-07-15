package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.dto.DeckRequestDto;
import com.example.demo.dto.DeckResponseDto;
import com.example.demo.entity.StudyDeck;
import com.example.demo.exception.StudyDeckNotFoundException;
import com.example.demo.repository.StudyDeckRepository;

@Service
public class DeckMangementServiceImpl implements DeckManagementService{
     @Autowired
    private StudyDeckRepository studyDeckRepository;

    public static DeckResponseDto convertToDeckResponseDto(StudyDeck studyDeck){
        DeckResponseDto dto=new DeckResponseDto();
        dto.setCapacity(studyDeck.getCapacity());
        dto.setDescription(studyDeck.getDescription());
        dto.setTitle(studyDeck.getTitle());
        dto.setId(studyDeck.getId());
        dto.setOwner(studyDeck.getOwner());
        dto.setMentorName(studyDeck.getMentorName());
        dto.setFlashcards(studyDeck.getFlashcards());
        return dto;
    }
    public List<DeckResponseDto> getAllDecks(){
        List<StudyDeck>li=studyDeckRepository.findAll();
        List<DeckResponseDto>result=new ArrayList<>();
        for(StudyDeck studyDeck : li){
            DeckResponseDto dto=convertToDeckResponseDto(studyDeck);
            result.add(dto);
        }
        return result;
    }


    public DeckResponseDto getDeckById(Long id){
        StudyDeck studyDeck=studyDeckRepository.findById(id).orElseThrow(()->new StudyDeckNotFoundException("Study deck with id"+id+" not found:("));
        return convertToDeckResponseDto(studyDeck);
    }

    public StudyDeck createDeck(DeckRequestDto dto){
        StudyDeck studyDeck=new StudyDeck();
        studyDeck.setTitle(dto.getTitle());
        studyDeck.setDescription(dto.getDescription());
        studyDeck.setCapacity(dto.getCapacity());
        studyDeck.setMentorName("to handle null");
        // studyDeck.setOwnerId();
        // studyDeck.setSystemUser();
        StudyDeck saved=studyDeckRepository.save(studyDeck);

        return saved;
    }

    public void deleteDeck(Long id){
        studyDeckRepository.deleteById(id);
    }

    public StudyDeck updateDeck(Long id,DeckRequestDto dto){
        StudyDeck studyDeck=studyDeckRepository.findById(id)
        .orElseThrow(()->new StudyDeckNotFoundException("no id found for updating study deck"));
        studyDeck.setTitle(dto.getTitle());
        studyDeck.setCapacity(dto.getCapacity());
        studyDeck.setDescription(dto.getDescription());
        // studyDeck.setMentorName();
        // studyDeck.setOwnerId();
        // studyDeck.setSystemUser();
        StudyDeck saved=studyDeckRepository.save(studyDeck);
        return saved;
    }
}
