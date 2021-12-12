package com.studenttest.trspo_test_management.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.studenttest.trspo_test_management.repo.model.Question;

public interface QuestionRepo extends JpaRepository<Question, Long> {
    @Query
    List<Question> findAllByTestId(long testId);
}
