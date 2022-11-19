package com.frailty.backend.question;

import javax.persistence.*;

@Entity
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    private String QuestionType;
    private Integer QuestionId;
    private String QuestionText;

    public Question() {

    }

    public Question(String questionType, String questionText) {
        this.QuestionType = questionType;
        this.QuestionText = questionText;
    }

    @Column(name = "question_type", nullable = false)
    public String getQuestionType() {
        return QuestionType;
    }
    @Column(name = "question", nullable = false)
    public String getQuestionText() {
        return QuestionText;
    }
}
