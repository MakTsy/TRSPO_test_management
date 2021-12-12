package com.studenttest.trspo_test_management.service;

import java.util.List;

import com.studenttest.trspo_test_management.repo.model.Question;

public interface QuestionManagementService {
    List<Question> fetchAllQuestionsByTestId(long testId);
    long createQuestion(long testId,String text);
    void updateQuestion(long id, Long testId,String text) throws IllegalArgumentException;
    void deleteQuestion(long id);
}
