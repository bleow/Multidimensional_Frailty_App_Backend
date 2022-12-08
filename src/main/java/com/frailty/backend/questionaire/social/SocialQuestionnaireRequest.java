package com.frailty.backend.questionaire.social;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Map;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class SocialQuestionnaireRequest {
    private final Map<Integer, String> answer;

}
