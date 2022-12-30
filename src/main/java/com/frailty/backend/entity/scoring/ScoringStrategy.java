package com.frailty.backend.entity.scoring;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "scoring_type", discriminatorType = DiscriminatorType.STRING)
public abstract class ScoringStrategy {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Double maxScore;

    @Column(nullable = false)
    private Double valuePerAns;

    @ElementCollection
    private List<String> correctAnswers;

    public ScoringStrategy(Double maxScore, Double valuePerAns, List<String> correctAnswers) {
        this.maxScore = maxScore;
        this.valuePerAns = valuePerAns;
        this.correctAnswers = correctAnswers;
    }

    public ScoringStrategy(Double maxScore, Double valuePerAns) {
        this.maxScore = maxScore;
        this.valuePerAns = valuePerAns;
    }

    public abstract Double calculateScore(List<String> answer);
    public abstract boolean validateScore(Double score);
}
