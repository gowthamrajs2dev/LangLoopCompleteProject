package com.example.demo.service;

import com.example.demo.entity.Flashcard;
import com.example.demo.entity.RetentionMetric;
import com.example.demo.entity.SystemUser;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.FlashcardRepository;
import com.example.demo.repository.RetentionMetricRepository;
import com.example.demo.repository.SystemUserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Implements the SM-2 spaced-repetition algorithm.
 *
 * Design note: classic SM-2 tracks a "repetitions" counter (0, 1, 2, 3...)
 * to decide whether an interval should be 1 day, 6 days, or previousInterval
 * * easeFactor. The SRS's RetentionMetric entity has no such counter field
 * -- only easeFactor, intervalDays, nextReviewDate, and masteryLevel -- so
 * this implementation uses intervalDays itself as the repetition-state
 * signal:
 *   intervalDays == 0  -> this is the first successful review  -> interval = 1
 *   intervalDays == 1  -> this is the second successful review -> interval = 6
 *   intervalDays >= 6  -> subsequent reviews -> interval = round(intervalDays * easeFactor)
 * A failed review (quality < 3) always resets intervalDays to 1.
 * masteryLevel is derived from the resulting interval rather than stored
 * independently, so it can't drift out of sync with the schedule.
 */
@Service
public class SpacedRepetitionServiceImpl implements SpacedRepetitionService {

    private static final double MIN_EASE_FACTOR = 1.3;
    private static final int MASTERED_INTERVAL_THRESHOLD_DAYS = 21;

    private final RetentionMetricRepository retentionMetricRepository;
    private final SystemUserRepository systemUserRepository;
    private final FlashcardRepository flashcardRepository;

    public SpacedRepetitionServiceImpl(RetentionMetricRepository retentionMetricRepository,
                                        SystemUserRepository systemUserRepository,
                                        FlashcardRepository flashcardRepository) {
        this.retentionMetricRepository = retentionMetricRepository;
        this.systemUserRepository = systemUserRepository;
        this.flashcardRepository = flashcardRepository;
    }

    @Override
    @Transactional
    public void updateRetentionMetric(Long userId, Long cardId, Integer quality) {
        if (quality == null || quality < 0 || quality > 5) {
            throw new IllegalArgumentException("Quality must be between 0 and 5");
        }

        RetentionMetric metric = retentionMetricRepository
                .findByUser_IdAndFlashcard_Id(userId, cardId)
                .orElseGet(() -> createNewMetric(userId, cardId));

        // EF' is recalculated on every review regardless of pass/fail,
        // per the standard SM-2 formula, then clamped to the 1.3 floor.
        double updatedEase = metric.getEaseFactor()
                + (0.1 - (5 - quality) * (0.08 + (5 - quality) * 0.02));
        if (updatedEase < MIN_EASE_FACTOR) {
            updatedEase = MIN_EASE_FACTOR;
        }
        metric.setEaseFactor(updatedEase);

        int newIntervalDays;
        if (quality < 3) {
            // Failed recall: reset the schedule, but keep the updated ease
            // factor so long-term difficulty tracking isn't lost.
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