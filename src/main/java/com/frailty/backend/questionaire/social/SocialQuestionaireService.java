package com.frailty.backend.questionaire.social;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class SocialQuestionaireService {

    public Map<Integer, String> getQuestions() {
        Map<Integer, String> res = new HashMap<>();
        res.put(0, "Do you sometimes visit your friend?");
        return res;
    }
}
