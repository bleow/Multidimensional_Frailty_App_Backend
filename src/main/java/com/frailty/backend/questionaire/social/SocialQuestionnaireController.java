package com.frailty.backend.questionaire.social;

import java.util.List;
import java.util.Map;

import com.frailty.backend.answer.Answer;
import com.frailty.backend.question.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Boolean> answers(@RequestBody Map<Integer, String> answer) {
        return ResponseEntity.ok(socialService.postAnswers(answer));
    }

    @GetMapping("/answers")
    public ResponseEntity<List<Answer>> answers() {
        return ResponseEntity.ok(socialService.getAnswers());
    }
}