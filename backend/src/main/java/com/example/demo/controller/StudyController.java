package com.example.demo.controller;

import com.example.demo.dto.DueReviewResponseDto;
import com.example.demo.dto.SessionResultDto;
import com.example.demo.dto.StudySessionResponseDto;
import com.example.demo.entity.RetentionMetric;
import com.example.demo.security.CustomUserDetails;
import com.example.demo.service.SpacedRepetitionService;
import com.example.demo.service.StudySessionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/study")
public class StudyController {

    private final SpacedRepetitionService spacedRepetitionService;
    private final StudySessionService studySessionService;

    public StudyController(SpacedRepetitionService spacedRepetitionService,
                            StudySessionService studySessionService) {
        this.spacedRepetitionService = spacedRepetitionService;
        this.studySessionService = studySessionService;
    }

    @GetMapping("/due")
    public ResponseEntity<List<DueReviewResponseDto>> getDueReviews(Authentication authentication) {
        Long userId = resolveUserId(authentication);

        // RetentionMetric carries a full SystemUser relationship (password
        // hash included), so it's mapped to a safe response DTO here rather
        // than serialized directly.
        List<DueReviewResponseDto> dueReviews = spacedRepetitionService.getDueReviews(userId).stream()
                .map(this::toDueReviewDto)
                .collect(Collectors.toList());

        return ResponseEntity.ok(dueReviews);
    }

    @PostMapping("/complete")
    public ResponseEntity<StudySessionResponseDto> completeSession(Authentication authentication,
                                                                     @Valid @RequestBody SessionResultDto dto) {
        // The authenticated principal's id overrides any client-supplied
        // userId, so a caller can't submit session results on someone
        // else's behalf.
        dto.setUserId(resolveUserId(authentication));
        return ResponseEntity.ok(studySessionService.completeSession(dto));
    }

    private Long resolveUserId(Authentication authentication) {
        // The principal is CustomUserDetails (set by JwtAuthenticationFilter
        // via CustomUserDetailsService), so the id is read directly here --
        // no extra database round-trip needed.
        CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();
        return principal.getId();
    }

    private DueReviewResponseDto toDueReviewDto(RetentionMetric metric) {
        return new DueReviewResponseDto(
                metric.getFlashcard().getId(),
                metric.getFlashcard().getFrontContent(),
                metric.getFlashcard().getBackContent(),
                metric.getNextReviewDate(),
                metric.getMasteryLevel().name()
        );
    }
}