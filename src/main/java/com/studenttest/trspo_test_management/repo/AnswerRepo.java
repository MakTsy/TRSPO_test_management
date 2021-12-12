package com.studenttest.trspo_test_management.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.studenttest.trspo_test_management.repo.model.Answer;

public interface AnswerRepo extends JpaRepository<Answer, Long> {
    @Query
    List<Answer> findAllByQuestionId(long questionId);
}
