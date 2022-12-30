package com.frailty.backend.repository;

import com.frailty.backend.entity.Answer;
import com.frailty.backend.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, UUID> {
    List<Answer> findByAppUser(AppUser patient);
}
