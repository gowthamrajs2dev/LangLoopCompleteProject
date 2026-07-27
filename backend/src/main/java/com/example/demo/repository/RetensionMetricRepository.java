package com.example.demo.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.RetentionMetric;

public interface RetensionMetricRepository extends JpaRepository<RetentionMetric,Long>{
    List<RetentionMetric> findByUser_IdAndNextReviewDataLessThanEqualsOrderByNextReviewDataAsc(Long userId,LocalDateTime asOf);
    Optional<RententionMetric> findByUser_IdAndFlashcard_Id(Long userId,Long flash)

}
