package com.frailty.backend.questionaire.social;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.frailty.backend.answer.Answer;
import com.frailty.backend.answer.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SocialQuestionaireService {
    @Autowired
    private AnswerRepository answerRepository;

    public Map<Integer, String> getQuestions() {
        Map<Integer, String> res = new HashMap<>();
        res.put(0, "Do you sometimes visit your friend?");
        return res;
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
