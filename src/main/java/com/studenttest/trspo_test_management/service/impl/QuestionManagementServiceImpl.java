package com.studenttest.trspo_test_management.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.studenttest.trspo_test_management.repo.QuestionRepo;
import com.studenttest.trspo_test_management.repo.model.Question;
import com.studenttest.trspo_test_management.service.QuestionManagementService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class QuestionManagementServiceImpl implements QuestionManagementService {

    private final QuestionRepo questionRepo;

    public List<Question> fetchAllQuestionsByTestId(long testId) {
        return questionRepo.findAllByTestId(testId);
    }

    public long createQuestion(long testId,String text) {
        final Question question = new Question(testId, text);
        final Question savedQuestion = questionRepo.save(question);

        return savedQuestion.getId();
    }

    public void updateQuestion(long id, Long testId,String text) throws IllegalArgumentException {
        final Optional<Question> maybeQuestion = questionRepo.findById(id);

        if(maybeQuestion.isEmpty())
            throw new IllegalArgumentException("Invalid question ID");

        final Question question = maybeQuestion.get();
        if (testId != null) question.setTestId(testId);
        if (text != null && !text.isBlank()) question.setText(text);
        questionRepo.save(question);
    }

    public void deleteQuestion(long id) {
        questionRepo.deleteById(id);
    }
}
