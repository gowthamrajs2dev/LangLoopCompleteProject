package com.example.demo.service;

import com.example.demo.dto.DeckRequestDto;
import com.example.demo.dto.DeckResponseDto;
import com.example.demo.entity.StudyDeck;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.StudyDeckRepository;
import com.example.demo.security.CustomUserDetails;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeckManagementServiceImpl implements DeckManagementService {

    private final StudyDeckRepository studyDeckRepository;

    public DeckManagementServiceImpl(StudyDeckRepository studyDeckRepository) {
        this.studyDeckRepository = studyDeckRepository;
    }

    @Override
    public List<DeckResponseDto> getAllDecks() {
        return studyDeckRepository.findAll().stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public DeckResponseDto getDeckById(Long id) {
        return toResponseDto(findDeckOrThrow(id));
    }

    @Override
    @PreAuthorize("hasAnyRole('LINGUIST', 'ADMIN')")
    @Transactional
    public DeckResponseDto createDeck(DeckRequestDto dto) {
        // DeckRequestDto (per SRS) carries only title/description/capacity.
        // mentorName and ownerId are derived from the authenticated caller,
        // since the SRS's own entity requires both as non-blank fields.
        CustomUserDetails owner = currentAuthenticatedUser();

        StudyDeck deck = new StudyDeck();
        deck.setTitle(dto.getTitle());
        deck.setDescription(dto.getDescription());
        deck.setCapacity(dto.getCapacity());
        deck.setMentorName(owner.getUsername());
        deck.setOwnerId(owner.getId());

        return toResponseDto(studyDeckRepository.save(deck));
    }

    @Override
    @PreAuthorize("hasAnyRole('LINGUIST', 'ADMIN')")
    @Transactional
    public DeckResponseDto updateDeck(Long id, DeckRequestDto dto) {
        StudyDeck deck = findDeckOrThrow(id);
        deck.setTitle(dto.getTitle());
        deck.setDescription(dto.getDescription());
        deck.setCapacity(dto.getCapacity());
        return toResponseDto(studyDeckRepository.save(deck));
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    @Transactional
    public void deleteDeck(Long id) {
        StudyDeck deck = findDeckOrThrow(id);
        // cascade = ALL, orphanRemoval = true on StudyDeck.flashcards ensures
        // associated flashcards are removed along with the deck.
        studyDeckRepository.delete(deck);
    }

    private StudyDeck findDeckOrThrow(Long id) {
        return studyDeckRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("StudyDeck not found with id: " + id));
    }

    private CustomUserDetails currentAuthenticatedUser() {
        // The principal is CustomUserDetails (set by JwtAuthenticationFilter
        // via CustomUserDetailsService), so id/username are read directly
        // here -- no extra database round-trip needed.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (CustomUserDetails) authentication.getPrincipal();
    }

    private DeckResponseDto toResponseDto(StudyDeck deck) {
        return new DeckResponseDto(
                deck.getId(),
                deck.getTitle(),
                deck.getDescription(),
                deck.getCapacity(),
                deck.getMentorName(),
                deck.getOwnerId()
        );
    }
}