package com.example.demo.controller;

import com.example.demo.dto.DeckRequestDto;
import com.example.demo.dto.DeckResponseDto;
import com.example.demo.service.DeckManagementService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/decks")
public class DeckController {

    private final DeckManagementService deckManagementService;

    public DeckController(DeckManagementService deckManagementService) {
        this.deckManagementService = deckManagementService;
    }

    @GetMapping
    public ResponseEntity<List<DeckResponseDto>> getAllDecks() {
        return ResponseEntity.ok(deckManagementService.getAllDecks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DeckResponseDto> getDeckById(@PathVariable Long id) {
        return ResponseEntity.ok(deckManagementService.getDeckById(id));
    }

    // Enforcement also lives in DeckManagementServiceImpl via @PreAuthorize;
    // repeating it here is intentional defense-in-depth so the rule is
    // visible at the API boundary too, not only in the service.
    @PostMapping
    @PreAuthorize("hasAnyRole('LINGUIST', 'ADMIN')")
    public ResponseEntity<DeckResponseDto> createDeck(@Valid @RequestBody DeckRequestDto dto) {
        DeckResponseDto created = deckManagementService.createDeck(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('LINGUIST', 'ADMIN')")
    public ResponseEntity<DeckResponseDto> updateDeck(@PathVariable Long id,
                                                       @Valid @RequestBody DeckRequestDto dto) {
        return ResponseEntity.ok(deckManagementService.updateDeck(id, dto));
    }

    // Must return the exact plain-text body "StudyDeck deleted successfully."
    // as a String, not a JSON object, per the SRS.
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteDeck(@PathVariable Long id) {
        deckManagementService.deleteDeck(id);
        return ResponseEntity.ok()
                .contentType(MediaType.TEXT_PLAIN)
                .body("StudyDeck deleted successfully.");
    }
}