package com.frailty.backend.answer;

import javax.persistence.*;

@Entity
@Table(name="answers")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

//    private Long user_id;
    private Integer QuestionId;
//    private QuestionType question_type;
    private String AnswerString;

    // default constructor when returning with id
    public Answer() {
    }

    // constructor when posting to db
    public Answer(Integer question_id, String answer) {
        this.QuestionId = question_id;
        this.AnswerString = answer;
    }

    @Column(name = "question_id", nullable = false)
    public Integer getQuestionId() {
        return QuestionId;
    }
    @Column(name = "answer", nullable = false)
    public String getAnswerString() {
        return AnswerString;
    }
}
