package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.neo4j.Neo4jProperties.Authentication;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.dto.DeckRequestDto;
import com.example.demo.dto.DeckResponseDto;
import com.example.demo.entity.StudyDeck;
import com.example.demo.entity.SystemUser;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.StudyDeckRepository;
import com.example.demo.repository.SystemUserRepository;

import jakarta.transaction.Transactional;

@Service
public class DeckMangementServiceImpl implements DeckManagementService{
    @Autowired
    private StudyDeckRepository studyDeckRepository;
    @Autowired
    private SystemUserRepository systemUserRepository;

    private SystemUser currentAuthenticatedUser(){
        Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        return systemUserRepository.findByUsername(authentication.getName())
        .orElseThrow(()->new ResourceNotFoundException("Authencticated user not found : "+authentication.getName()));

    }

    private DeckResponseDto toResponseDto(StudyDeck deck){
        return new DeckResponseDto(
            deck.getId(),
            deck.getTitle(),
            deck.getDescription(),
            deck.getCapacity(),
            deck.getMentorName(),
            deck.getOwnerId()
        );
    }

    @Override
    public List<DeckResponseDto> getAllDecks(){
        return studyDeckRepository.findAll().stream()
        .map(this::toResponseDto)
        .collect(Collectors.toList());
    }
    private StudyDeck findDeckOrThrow(Long id){
        StudyDeck studyDeck=studyDeckRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("StudyDeck not found with id: "+id));
        return studyDeck;
    }
    @Override
    public DeckResponseDto getDeckById(Long id){
        return toResponseDto(findDeckOrThrow(id));
    }

    @Override
    @PreAuthorize("hasAnyRole('LINGUIST','ADMIN')")
    @Transactional
    public DeckResponseDto createDeck(DeckRequestDto dto){
        SystemUser owner= currentAuthenticatedUser();
        StudyDeck deck = new StudyDeck();
        deck.setTitle(dto.getTitle());
    }
}
