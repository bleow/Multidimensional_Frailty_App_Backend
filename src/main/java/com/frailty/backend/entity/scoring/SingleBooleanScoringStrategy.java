package com.frailty.backend.entity.scoring;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.util.List;


@Slf4j
@Entity
@Getter
@Setter
@NoArgsConstructor
@DiscriminatorValue("BOOLEAN")
public class SingleBooleanScoringStrategy extends ScoringStrategy implements IScoringStrategy {
    public SingleBooleanScoringStrategy(Double maxScore, Double valuePerAns, List<String> correctAnswer) {
        super(maxScore, valuePerAns, correctAnswer);
    }

    public Double calculateScore(List<String> givenAnswer) {
        Boolean correctAns = Boolean.valueOf(this.getCorrectAnswers().get(0));
        Double givenScore = 0.0;
        for (int i = 0; i < givenAnswer.size(); i++) {
            Boolean ans = Boolean.valueOf(givenAnswer.get(0));
            if (ans==correctAns) {
                givenScore += this.getValuePerAns();
            }
        }
        if (validateScore(givenScore)) {
            return givenScore;
        }
        log.warn("Score is {}, rounding down to max score of {}", givenScore, this.getMaxScore());
        return this.getMaxScore();
    }

    public boolean validateScore(Double score) {
        if (score > this.getMaxScore()) {
            return false;
        }
        return true;
    }
}
