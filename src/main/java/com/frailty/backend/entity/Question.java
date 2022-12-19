package com.frailty.backend.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private QuestionType questionType;
    // https://stackoverflow.com/questions/55166779/how-to-remove-some-fields-of-an-object-in-spring-boot-response-control

    @Column(nullable = false)
    private Integer questionId;

    @Column(nullable = false)
    private String questionText;

    @Column(nullable = false)
    private Integer maxScore;

    public Question(QuestionType questionType, Integer questionId, String questionText, Integer maxScore) {
        this.questionType = questionType;
        this.questionId = questionId;
        this.questionText = questionText;
        this.maxScore = maxScore;
    }
}
