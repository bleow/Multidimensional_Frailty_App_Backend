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
@DiscriminatorValue("FIRST_LETTER")
public class FirstLetterMatchScoringStrategy extends ScoringStrategy implements IScoringStrategy {
    public FirstLetterMatchScoringStrategy(Double maxScore, Double valuePerAns, List<String> correctAnswer) {
        super(maxScore, valuePerAns, correctAnswer);
    }

    public Double calculateScore(List<String> givenAnswer) {
        Character startsWith = this.getCorrectAnswers().get(0).charAt(0);
        Double score = 0.0;
        for (int i = 0; i < givenAnswer.size(); i++) {
            Character userStartsWith = givenAnswer.get(i).charAt(0);
            if (userStartsWith == startsWith) {
                score += this.getValuePerAns();
            }
            if (!validateScore(score)) {
                log.warn("Score is {}, rounding down to max score of {}", score, this.getMaxScore());
                return this.getMaxScore();
            }
        }
        return score;
    }

    public boolean validateScore(Double score) {
        if (score > this.getMaxScore()) {
            return false;
        }
        return true;
    }
}
