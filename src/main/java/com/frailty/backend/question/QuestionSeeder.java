package com.frailty.backend.question;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class QuestionSeeder implements CommandLineRunner {
    private ArrayList<Question> questions;

    public QuestionSeeder() {
        questions = new ArrayList<Question>();
    }

    @Autowired
    QuestionRepository questionRepository;

    @Override
    public void run(String... args) throws Exception {
        loadCognitiveQuestionnaire();
        loadPhysicalQuestionnaire();
        loadSocialQuestionnaire();
        questionRepository.saveAll(questions);
    }

    private void loadCognitiveQuestionnaire() {
        questions.add(new Question(QuestionType.COGNITIVE, 1, "Repeat the 5 words immediately: "));
        questions.add(new Question(QuestionType.COGNITIVE, 2, "Name as many words in one minute that begin with the letter: "));
        questions.add(new Question(QuestionType.COGNITIVE, 3, "Answer the following what/where questions: " +
                "\n 1) Date \n 2) Month \n 3) Day \n 4) Place \n 5) City"));
        questions.add(new Question(QuestionType.COGNITIVE, 4, "Recall the 5 words from the first task. "));
    }
    private void loadPhysicalQuestionnaire() {
        questions.add(new Question(QuestionType.PHYSICAL, 1, "During the past week, I felt that everything I did was an effort."));
        questions.add(new Question(QuestionType.PHYSICAL, 2, "By yourself and not using aids, do you have any difficulty walking up 10 stairs without resting?"));
        questions.add(new Question(QuestionType.PHYSICAL, 3, "Do you have any difficulty walking one lap of a playground track (400m)?"));
        questions.add(new Question(QuestionType.PHYSICAL, 4, "During the past week, how often did you participate in any moderate physical activities that make you slightly more breathless than usual, such as rapid walking, carrying a light item, cleaning, infant care?"));
        questions.add(new Question(QuestionType.PHYSICAL, 5, "During the past week, how often did you engage in vigorous physical activities, such as vigorous sports, carrying 20kg or more weights, carrying items up a set of stairs, digging, construction labouring?"));
        questions.add(new Question(QuestionType.PHYSICAL, 6, "Was there an unintended weight loss of 4.5 kg in the past year?"));
        questions.add(new Question(QuestionType.PHYSICAL, 7, "We will now perform the Chair Stand Test. How many stands did you perform in this one minute?"));
    }

    private void loadSocialQuestionnaire() {
        questions.add(new Question(QuestionType.SOCIAL, 1, "Do you sometimes visit your friend?"));
        questions.add(new Question(QuestionType.SOCIAL, 2, "Do you turn to family or friends for advice?"));
        questions.add(new Question(QuestionType.SOCIAL, 3, "Do you have someone to confide in?"));
        questions.add(new Question(QuestionType.SOCIAL, 4, "Do you go out less frequently compared with last year?"));
        questions.add(new Question(QuestionType.SOCIAL, 5, "Do you eat with someone at least one time in a day?"));
        questions.add(new Question(QuestionType.SOCIAL, 6, "Are you limited by your financial resources to pay for needed medical service?"));
        questions.add(new Question(QuestionType.SOCIAL, 7, "Do you live alone?"));
        questions.add(new Question(QuestionType.SOCIAL, 8, "Do you talk with someone every day?"));
    }
}
