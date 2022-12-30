package com.frailty.backend.repository;

import com.frailty.backend.entity.scoring.ScoringStrategy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoringStrategyRepository extends JpaRepository<ScoringStrategy, Integer> {
}
