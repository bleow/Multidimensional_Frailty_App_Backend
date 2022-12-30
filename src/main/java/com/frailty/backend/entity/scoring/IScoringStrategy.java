package com.frailty.backend.entity.scoring;

import java.util.List;

public interface IScoringStrategy {
    Double calculateScore(List<String> answer);
    boolean validateScore(Double score);
}
