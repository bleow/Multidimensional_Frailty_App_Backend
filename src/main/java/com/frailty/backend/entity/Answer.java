package com.frailty.backend.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private LocalDateTime time;

    @Column(nullable = false)
    private String answerText;

    @Column(nullable = false)
    private Integer answerScore;

    @OneToOne
    @JoinColumn(nullable = false)
    private AppUser appUser;

    @OneToOne
    @JoinColumn(nullable = false)
    private Question question;



    // constructor when retrieving from db
    public Answer() {
    }

    // constructor when posting to db
    public Answer(AppUser appUser, LocalDateTime time, Question question, String answerText, Integer answerScore) {
        this.appUser = appUser;
        this.time = time;
        this.question = question;
        this.answerText = answerText;
        this.answerScore = answerScore;
    }

}
