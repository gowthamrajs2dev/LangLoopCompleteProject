package com.example.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    private SpacedRepetitionService spacedRepetitionService;
    @Autowired
    private StudySessionService studySessionService;
    @Autowired
    private SystemUserRepository systemUserRepository;
    
    @GetMapping("/due")
    public ResponseEntity<List<DueReviewResponseDto>> getDueReviews(Authentication authentication) {
        Long userId = resolveUserId(authentication);
        List<DueReviewResponseDto> dueReviews = spacedRepetitionService.getDueReviews(userId).stream()
                .map(this::toDueReviewDto)
                .collect(Collectors.toList());
 
        return ResponseEntity.ok(dueReviews);
    }
 
    @PostMapping("/complete")
    public ResponseEntity<StudySessionResponseDto> completeSession(Authentication authentication,@Valid @RequestBody SessionResultDto dto) {
        dto.setUserId(resolveUserId(authentication));
        return ResponseEntity.ok(studySessionService.completeSession(dto));
    }
 
    private Long resolveUserId(Authentication authentication) {
        SystemUser user = systemUserRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Authenticated user not found: " + authentication.getName()));
        return user.getId();
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
