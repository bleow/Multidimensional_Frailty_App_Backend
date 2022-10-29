package com.frailty.backend.questionaire.social;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "api/v1/social")
@AllArgsConstructor
public class SocialQuestionaireController {
    private SocialQuestionaireService socialService;

    @GetMapping
    public Map<Integer, String> questions() {
        return socialService.getQuestions();
    }

}