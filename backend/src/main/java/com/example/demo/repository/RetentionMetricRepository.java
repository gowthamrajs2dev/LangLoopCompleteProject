package com.example.demo.repository;

import com.example.demo.entity.RetentionMetric;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface RetentionMetricRepository extends JpaRepository<RetentionMetric, Long> {

    // Backs SpacedRepetitionService.getDueReviews(userId): every metric for
    // this learner whose next review date has already arrived.
    List<RetentionMetric> findByUser_IdAndNextReviewDateLessThanEqualOrderByNextReviewDateAsc(
            Long userId, LocalDateTime asOf);

    // Backs SpacedRepetitionService.updateRetentionMetric(userId, cardId, quality):
    // locates the single metric row tying a learner to a specific flashcard.
    Optional<RetentionMetric> findByUser_IdAndFlashcard_Id(Long userId, Long flashcardId);

    // JPQL alternative demonstrating an explicit due-reviews query filtered
    // by nextReviewDate, as called out in the SRS.
    @Query("SELECT r FROM RetentionMetric r WHERE r.user.id = :userId AND r.nextReviewDate <= :now")
    List<RetentionMetric> findDueForUser(@Param("userId") Long userId, @Param("now") LocalDateTime now);
}