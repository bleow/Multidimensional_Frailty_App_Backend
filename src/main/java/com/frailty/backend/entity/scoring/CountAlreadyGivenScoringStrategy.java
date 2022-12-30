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
@DiscriminatorValue("COUNT_GIVEN")
public class CountAlreadyGivenScoringStrategy extends ScoringStrategy implements IScoringStrategy {
    public CountAlreadyGivenScoringStrategy(Double maxScore, Double valuePerAns) {
        super(maxScore, valuePerAns);
    }

    public Double calculateScore(List<String> givenAnswer) {
        Double givenScore = 0.0;
        for (int i = 0; i < givenAnswer.size(); i++) {
            givenScore += Double.valueOf(givenAnswer.get(i)) * this.getValuePerAns();
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
