package com.frailty.backend.repository;

import com.frailty.backend.entity.Question;
import com.frailty.backend.entity.QuestionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
    List<Question> findByQuestionType(QuestionType questionType);
}