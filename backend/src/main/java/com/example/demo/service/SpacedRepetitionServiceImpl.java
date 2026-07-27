package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.entity.Flashcard;
import com.example.demo.entity.RetentionMetric;
import com.example.demo.entity.SystemUser;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.FlashcardRepository;
import com.example.demo.repository.RetentionMetricRepository;
import com.example.demo.repository.SystemUserRepository;

import jakarta.transaction.Transactional;

public class SpacedRepetitionServiceImpl implements SpacedRepetitionService {
    private static final double MIN_EASE_FACTOR = 1.3;
    private static final int MASTERED_INTERVAL_THRESHOLD_DAYS = 21;
 
    @Autowired
    private RetentionMetricRepository retentionMetricRepository;
    @Autowired
    private SystemUserRepository systemUserRepository;
    @Autowired
    private FlashcardRepository flashcardRepository;
    @Override
    @Transactional
    public void updateRetentionMetric(Long userId, Long cardId, Integer quality) {
        if (quality == null || quality < 0 || quality > 5) {
            throw new IllegalArgumentException("Quality must be between 0 and 5");
        }
 
        RetentionMetric metric = retentionMetricRepository
                .findByUser_IdAndFlashcard_Id(userId, cardId)
                .orElseGet(() -> createNewMetric(userId, cardId));
        
        double updatedEase = metric.getEaseFactor()
                + (0.1 - (5 - quality) * (0.08 + (5 - quality) * 0.02));
        if (updatedEase < MIN_EASE_FACTOR) {
            updatedEase = MIN_EASE_FACTOR;
        }
        metric.setEaseFactor(updatedEase);
 
        int newIntervalDays;
        if (quality < 3) {
            newIntervalDays = 1;
        } else if (metric.getIntervalDays() == null || metric.getIntervalDays() == 0) {
            newIntervalDays = 1;
        } else if (metric.getIntervalDays() == 1) {
            newIntervalDays = 6;
        } else {
            newIntervalDays = (int) Math.round(metric.getIntervalDays() * updatedEase);
        }
 
        metric.setIntervalDays(newIntervalDays);
        metric.setNextReviewDate(LocalDateTime.now().plusDays(newIntervalDays));
        metric.setMasteryLevel(deriveMasteryLevel(quality, newIntervalDays));
 
        retentionMetricRepository.save(metric);
    }
 
    @Override
    public List<RetentionMetric> getDueReviews(Long userId) {
        return retentionMetricRepository
                .findByUser_IdAndNextReviewDateLessThanEqualOrderByNextReviewDateAsc(
                        userId, LocalDateTime.now());
    }
 
    private RetentionMetric createNewMetric(Long userId, Long cardId) {
        SystemUser user = systemUserRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("SystemUser not found with id: " + userId));
        Flashcard flashcard = flashcardRepository.findById(cardId)
                .orElseThrow(() -> new ResourceNotFoundException("Flashcard not found with id: " + cardId));
 
        return new RetentionMetric(user, flashcard);
    }
 
    private RetentionMetric.MasteryLevel deriveMasteryLevel(int quality, int intervalDays) {
        if (quality < 3) {
            return RetentionMetric.MasteryLevel.LEARNING;
        }
        if (intervalDays >= MASTERED_INTERVAL_THRESHOLD_DAYS) {
            return RetentionMetric.MasteryLevel.MASTERED;
        }
        return RetentionMetric.MasteryLevel.LEARNING;
    }

}
