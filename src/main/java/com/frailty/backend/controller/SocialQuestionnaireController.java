package com.frailty.backend.controller;

import java.util.List;

import com.frailty.backend.dto.AnswerRequest;
import com.frailty.backend.entity.Answer;
import com.frailty.backend.entity.Question;
import com.frailty.backend.service.SocialQuestionnaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "api/v1/social")
@AllArgsConstructor
public class SocialQuestionnaireController {
    @Autowired
    private SocialQuestionnaireService socialService;

    @GetMapping
    public List<Question> all() {
        return socialService.getQuestions();
    }

    @PostMapping
    public ResponseEntity<Double> answers(Authentication authentication, @RequestBody List<AnswerRequest> answer) {
        return ResponseEntity.ok(socialService.postAnswers(authentication.getName(), answer));
    }
    // https://stackoverflow.com/questions/49529760/springboot-get-username-from-authentication-via-controller

    @GetMapping(path="answers")
    public ResponseEntity<List<Answer>> answers(Authentication authentication, @RequestParam("patient") String email) {
        return ResponseEntity.ok(socialService.getAnswers(authentication.getName(), email));
    }
}