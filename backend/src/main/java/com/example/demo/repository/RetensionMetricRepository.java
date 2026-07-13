package com.example.demo.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.RetentionMetric;

public interface RetensionMetricRepository extends JpaRepository<RetentionMetric,Long>{
    public List<RetentionMetric> findByNextReviewDate(LocalDateTime nextReviewDate);
}
