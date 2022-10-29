package com.frailty.backend.questionaire;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Getter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public abstract class QuestionaireRequest {
    private String questionType;
    private Integer questionId;
    private String questionText;
}
