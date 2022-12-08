package com.frailty.backend.questionaire.social;

import java.util.List;
import java.util.Map;

import com.frailty.backend.answer.Answer;
import com.frailty.backend.answer.AnswerRepository;
import com.frailty.backend.question.Question;
import com.frailty.backend.question.QuestionRepository;
import com.frailty.backend.question.QuestionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SocialQuestionnaireService {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private AnswerRepository answerRepository;

    public List<Question> getQuestions() {
        List<Question> socialQuestions = questionRepository.findByQuestionType(QuestionType.SOCIAL);
        return socialQuestions;
    }

    public Boolean postAnswers(Map<Integer, String> answer) {
        for (Map.Entry<Integer, String> entry : answer.entrySet()) {
            Answer ans = new Answer(entry.getKey(), entry.getValue());
            answerRepository.save(ans);
        }
        return Boolean.TRUE;
    }

    public List<Answer> getAnswers() {
        return answerRepository.findAll();
    }
}
