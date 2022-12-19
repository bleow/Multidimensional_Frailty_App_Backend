package com.frailty.backend.service;

import java.util.List;

import com.frailty.backend.dto.QuestionnaireRequest;
import com.frailty.backend.entity.AppUser;
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

    public Boolean postAnswers(String username, QuestionnaireRequest answer) {
        log.warn("==TESTING== USERNAME {}", username);
        AppUser appUser = appUserService.getValidUser(username);

        return true;
    }

}
