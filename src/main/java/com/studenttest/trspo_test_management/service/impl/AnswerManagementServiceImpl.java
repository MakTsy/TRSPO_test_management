package com.studenttest.trspo_test_management.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.studenttest.trspo_test_management.repo.AnswerRepo;
import com.studenttest.trspo_test_management.repo.model.Answer;
import com.studenttest.trspo_test_management.service.AnswerManagementService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AnswerManagementServiceImpl implements AnswerManagementService {

    private final AnswerRepo answerRepo;

    public List<Answer> fetchAllAnswersByQuestionId(long questionId) {
        return answerRepo.findAllByQuestionId(questionId);
    }

    public long createAnswer(long questionId,String text, boolean isCorrect) {
        final Answer answer = new Answer(questionId, text, isCorrect);
        final Answer savedAnswer = answerRepo.save(answer);

        return savedAnswer.getId();
    }

    public void updateAnswer(long id, Long questionId,String text, Boolean isCorrect) throws IllegalArgumentException {
        final Optional<Answer> maybeAnswer = answerRepo.findById(id);

        if(maybeAnswer.isEmpty())
            throw new IllegalArgumentException("Invalid question ID");

        final Answer answer = maybeAnswer.get();
        if (questionId != null) answer.setQuestionId(questionId);
        if (text != null && !text.isBlank()) answer.setText(text);
        if (isCorrect != null) answer.setCorrect(isCorrect);
        answerRepo.save(answer);
    }

    public void deleteAnswer(long id) {
        answerRepo.deleteById(id);
    }
}
