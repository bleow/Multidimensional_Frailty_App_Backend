package com.frailty.backend.entity.scoring;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Slf4j
@Entity
@Getter
@Setter
@NoArgsConstructor
@DiscriminatorValue("EXACT_MATCH")
public class ExactMatchScoringStrategy extends ScoringStrategy implements IScoringStrategy {
    public ExactMatchScoringStrategy(Double maxScore, Double valuePerAns, List<String> correctAnswer) {
        super(maxScore, valuePerAns, correctAnswer);
    }

    public Double calculateScore(List<String> givenAnswer) {
        Set<String> result = givenAnswer.stream()
                .distinct()
                .filter(this.getCorrectAnswers()::contains)
                .collect(Collectors.toSet());
        Double score = result.size() * this.getValuePerAns();
        if (validateScore(score)) {
            return score;
        }
        log.warn("Score is {}, rounding down to max score of {}", score, this.getMaxScore());
        return this.getMaxScore();
    }

    public boolean validateScore(Double score) {
        if (score > this.getMaxScore()) {
            return false;
        }
        return true;
    }
}
