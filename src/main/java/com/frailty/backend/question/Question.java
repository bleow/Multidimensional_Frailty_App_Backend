package com.frailty.backend.question;

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

    private QuestionType questionType;
    private Integer questionId;
    private String questionText;

    public Question(QuestionType questionType, Integer questionId, String questionText) {
        this.questionType = questionType;
        this.questionId = questionId;
        this.questionText = questionText;
    }
}
