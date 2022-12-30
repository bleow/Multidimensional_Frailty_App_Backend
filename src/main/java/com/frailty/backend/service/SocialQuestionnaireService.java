package com.frailty.backend.service;

import java.util.ArrayList;
import java.util.List;

import com.frailty.backend.dto.AnswerRequest;
import com.frailty.backend.entity.Answer;
import com.frailty.backend.entity.AppUser;
import com.frailty.backend.entity.scoring.ScoringStrategy;
import com.frailty.backend.output.Localiser;
import com.frailty.backend.repository.AnswerRepository;
import com.frailty.backend.entity.Question;
import com.frailty.backend.repository.QuestionRepository;
import com.frailty.backend.entity.QuestionType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SocialQuestionnaireService {
    private Localiser localiser;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private AppUserService appUserService;

    public List<Question> getQuestions() {
        List<Question> socialQuestions = questionRepository.findByQuestionType(QuestionType.SOCIAL);
        return socialQuestions;
    }

    public Double postAnswers(String username, List<AnswerRequest> answerRequest) {
        log.warn("==TESTING== USERNAME {}", username);
        AppUser appUser = appUserService.getValidUser(username);
        ArrayList<Answer> answers = new ArrayList<>();
        Double totalScore = 0.0;
        for (AnswerRequest request : answerRequest) {
            Question question = questionRepository.findByQuestionNumberAndQuestionType(request.questionNumber(), QuestionType.SOCIAL).orElseThrow(() -> new IllegalStateException(localiser.notFound("Question ID", request.questionNumber().toString())));
            ScoringStrategy strategy = question.getStrategy();
            log.warn("==TESTING== QUESTION {} {}", question.getQuestionText(), strategy.getCorrectAnswers().toString());
            Double answerScore = strategy.calculateScore(request.answerText());
            totalScore += answerScore;
            Answer answer = new Answer(appUser, request.datetime(), question, request.answerTextToString(), answerScore);
            answers.add(answer);
        }
        answerRepository.saveAll(answers);

        return totalScore;
    }

    public List<Answer> getAnswers(String username, String email) {
        AppUser clinician = appUserService.getValidUser(username);
        AppUser patient = appUserService.getValidUser(email);
        return answerRepository.findByAppUser(patient);
    }
}
