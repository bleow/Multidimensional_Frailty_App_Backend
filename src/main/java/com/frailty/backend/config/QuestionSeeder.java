package com.frailty.backend.config;

import com.frailty.backend.entity.Question;
import com.frailty.backend.entity.QuestionType;
import com.frailty.backend.entity.scoring.*;
import com.frailty.backend.repository.QuestionRepository;
import com.frailty.backend.repository.ScoringStrategyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class QuestionSeeder implements CommandLineRunner {
    private ArrayList<Question> questions;

    public QuestionSeeder() {
        questions = new ArrayList<Question>();
    }

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    ScoringStrategyRepository scoringStrategyRepository;

    @Override
    public void run(String... args) throws Exception {
        loadCustomScoringQuestionnaire();
        loadBooleanQuestionnaire();
        questionRepository.saveAll(questions);
    }

    private void loadCustomScoringQuestionnaire() {
        List<String> ar = new ArrayList<String>();
        ar.add("Face"); ar.add("Velvet"); ar.add("Church"); ar.add("Daisy"); ar.add("Red");
        ScoringStrategy strategy = new ExactMatchScoringStrategy(5.0, 1.0, ar);
        ScoringStrategy repeatNWords = scoringStrategyRepository.save(strategy);
        questions.add(new Question(QuestionType.COGNITIVE, 1, "Repeat the 5 words immediately: Face; Velvet; Church; Daisy; Red", repeatNWords));

        ar.clear();
        ar.add("F");
        strategy = new FirstLetterMatchScoringStrategy(9.0, 0.5, ar);
        ScoringStrategy startsWithK = scoringStrategyRepository.save(strategy);
        questions.add(new Question(QuestionType.COGNITIVE, 2, "Name as many words in one minute that begin with the letter: F", startsWithK));

        ar.clear();
        ar.add("CALCULATE_DATE"); ar.add("CALCULATE_MONTH"); ar.add("CALCULATE_DAY"); ar.add("CALCULATE_PLACE"); ar.add("CALCULATE_CITY");
        strategy = new ExactMatchScoringStrategy(5.0, 1.0, ar);
        ScoringStrategy timeAndPlace = scoringStrategyRepository.save(strategy);
        questions.add(new Question(QuestionType.COGNITIVE, 3, "Answer the following what/where questions: " +
                "\n 1) Date \n 2) Month \n 3) Day \n 4) Place \n 5) City", timeAndPlace));

        ar.clear();
        ar.add("Face"); ar.add("Velvet"); ar.add("Church"); ar.add("Daisy"); ar.add("Red");
        strategy = new ExactMatchScoringStrategy(10.0, 2.0, ar);
        ScoringStrategy recallNWords = scoringStrategyRepository.save(strategy);
        questions.add(new Question(QuestionType.COGNITIVE, 4, "Recall the 5 words from the first task. ", recallNWords));

        ar.clear();
        strategy = new CountAlreadyGivenScoringStrategy(10.0, 1.0);
        ScoringStrategy chairStand = scoringStrategyRepository.save(strategy);
        questions.add(new Question(QuestionType.PHYSICAL, 7, "We will now perform the Chair Stand Test. How many stands did you perform in this one minute?", chairStand));
    }

    private void loadBooleanQuestionnaire() {
        List<String> TRUE = Arrays.asList("true");
        ScoringStrategy strategy = new SingleBooleanScoringStrategy(1.0, 1.0, TRUE);
        ScoringStrategy yesScoringStrategy = scoringStrategyRepository.save(strategy);
        questions.add(new Question(QuestionType.PHYSICAL, 1, "During the past week, I felt that everything I did was an effort.", yesScoringStrategy));
        questions.add(new Question(QuestionType.PHYSICAL, 2, "By yourself and not using aids, do you have any difficulty walking up 10 stairs without resting?", yesScoringStrategy));
        questions.add(new Question(QuestionType.PHYSICAL, 3, "Do you have any difficulty walking one lap of a playground track (400m)?", yesScoringStrategy));
        questions.add(new Question(QuestionType.PHYSICAL, 4, "During the past week, how often did you participate in any moderate physical activities that make you slightly more breathless than usual, such as rapid walking, carrying a light item, cleaning, infant care?", yesScoringStrategy));
        questions.add(new Question(QuestionType.PHYSICAL, 5, "During the past week, how often did you engage in vigorous physical activities, such as vigorous sports, carrying 20kg or more weights, carrying items up a set of stairs, digging, construction labouring?", yesScoringStrategy));
        questions.add(new Question(QuestionType.PHYSICAL, 6, "Was there an unintended weight loss of 4.5 kg in the past year?", yesScoringStrategy));

        questions.add(new Question(QuestionType.SOCIAL, 1, "Do you sometimes visit your friend?", yesScoringStrategy));
        questions.add(new Question(QuestionType.SOCIAL, 2, "Do you turn to family or friends for advice?", yesScoringStrategy));
        questions.add(new Question(QuestionType.SOCIAL, 3, "Do you have someone to confide in?", yesScoringStrategy));
        questions.add(new Question(QuestionType.SOCIAL, 4, "Do you go out less frequently compared with last year?", yesScoringStrategy));
        questions.add(new Question(QuestionType.SOCIAL, 5, "Do you eat with someone at least one time in a day?", yesScoringStrategy));
        questions.add(new Question(QuestionType.SOCIAL, 6, "Are you limited by your financial resources to pay for needed medical service?", yesScoringStrategy));
        questions.add(new Question(QuestionType.SOCIAL, 7, "Do you live alone?", yesScoringStrategy));
        questions.add(new Question(QuestionType.SOCIAL, 8, "Do you talk with someone every day?", yesScoringStrategy));
    }
}
