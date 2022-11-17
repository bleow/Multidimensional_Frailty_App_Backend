package com.frailty.backend.question;

import com.frailty.backend.answer.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Answer, Integer> {
}
