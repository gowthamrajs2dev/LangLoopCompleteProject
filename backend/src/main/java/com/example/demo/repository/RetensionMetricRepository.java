package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.RetentionMetric;

public interface RetensionMetricRepository extends JpaRepository<Long,RetentionMetric>{
    
}
