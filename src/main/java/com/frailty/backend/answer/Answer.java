package com.frailty.backend.answer;

import javax.persistence.*;

@Entity
@Table(name="answers")
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

//    private Long user_id;
    private Integer questionId;
//    private QuestionType question_type;
    private String answerString;

    // default constructor when returning with id
    public Answer() {
    }

    // constructor when posting to db
    public Answer(Integer question_id, String answer) {
        this.questionId = question_id;
        this.answerString = answer;
    }

    @Column(name = "question_id", nullable = false)
    public Integer getQuestionId() {
        return questionId;
    }
    @Column(name = "answer", nullable = false)
    public String getAnswerString() {
        return answerString;
    }
}
