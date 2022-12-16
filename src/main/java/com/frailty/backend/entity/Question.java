package com.frailty.backend.entity;

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
    private QuestionType questionType;

    @Column(nullable = false)
    private Integer questionId;

    @Column(nullable = false)
    private String questionText;

    public Question(QuestionType questionType, Integer questionId, String questionText) {
        this.questionType = questionType;
        this.questionId = questionId;
        this.questionText = questionText;
    }
}
