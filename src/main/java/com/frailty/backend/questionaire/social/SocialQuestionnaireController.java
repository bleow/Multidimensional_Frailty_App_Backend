package com.frailty.backend.questionaire.social;

import java.util.List;
import java.util.Map;

import com.frailty.backend.answer.Answer;
import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "api/v1/social")
@AllArgsConstructor
public class SocialQuestionnaireController {
    private SocialQuestionaireService socialService;

    @GetMapping
    public Map<Integer, String> all() {
        return socialService.getQuestions();
    }

    @PostMapping
    public Boolean answers(@RequestBody Map<Integer, String> answer) { return socialService.postAnswers(answer); }

    @GetMapping("/answers")
    public List<Answer> answers() { return socialService.getAnswers(); }
}