package com.frailty.backend.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Answer {
    @Id
    @GeneratedValue()
    private UUID id;

    @Column(nullable = false)
    private LocalDateTime datetime;

    @Column(nullable = false)
    private String answerText;

    @Column(nullable = false)
    private Double answerScore;

    @OneToOne
    @JoinColumn(nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private AppUser appUser;

    @OneToOne
    @JoinColumn(nullable = false)
    private Question question;


    // constructor when posting to db
    public Answer(AppUser appUser, LocalDateTime datetime, Question question, String answerText, Double answerScore) {
        this.appUser = appUser;
        this.datetime = datetime;
        this.question = question;
        this.answerText = answerText;
        this.answerScore = answerScore;
    }

}
