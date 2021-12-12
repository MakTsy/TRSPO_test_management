package com.studenttest.trspo_test_management.service;

import java.util.List;

import com.studenttest.trspo_test_management.repo.model.Answer;

public interface AnswerManagementService {
    List<Answer> fetchAllAnswersByQuestionId(long questionId);
    long createAnswer(long questionId,String text, boolean isCorrect);
    void updateAnswer(long id, Long questionId,String text, Boolean isCorrect) throws IllegalArgumentException;
    void deleteAnswer(long id);
}
