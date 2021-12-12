package com.studenttest.trspo_test_management.api;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.studenttest.trspo_test_management.api.dto.Answer;
import com.studenttest.trspo_test_management.api.dto.Question;
import com.studenttest.trspo_test_management.api.dto.Test;
import com.studenttest.trspo_test_management.service.impl.AnswerManagementServiceImpl;
import com.studenttest.trspo_test_management.service.impl.QuestionManagementServiceImpl;
import com.studenttest.trspo_test_management.service.impl.TestManagementServiceImpl;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RequestMapping("/tests")
@RestController
public class TestManagementController {

    private final TestManagementServiceImpl testManagementService;
    private final QuestionManagementServiceImpl questionManagementService;
    private final AnswerManagementServiceImpl answerManagementService;

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody Test test) {
        final String title = test.title();
        final String description = test.description();
        final int timeInMinutes = test.timeInMinutes();
        final List<Question> questions = test.questions();
        final long testId = testManagementService.createTest(title, description, timeInMinutes);
        final String testUri = String.format("/tests/%d", testId);

        questions.forEach(question -> {
            long questionId = questionManagementService.createQuestion(testId, question.text());

            List<Answer> answers = question.answers();
            answers.forEach(answer -> answerManagementService.createAnswer(questionId, answer.text(), answer.isCorrect()));
        });

        return ResponseEntity.created(URI.create(testUri)).build();
    }

    @GetMapping
    public ResponseEntity<List<com.studenttest.trspo_test_management.repo.model.Test>> index() {
        final List<com.studenttest.trspo_test_management.repo.model.Test> tests = testManagementService.fetchAllTests();
        return ResponseEntity.ok(tests);
    }

    @GetMapping("/{testId}")
    public ResponseEntity<com.studenttest.trspo_test_management.repo.model.Test> fetchById(@PathVariable long testId) {
        final com.studenttest.trspo_test_management.repo.model.Test test = testManagementService.fetchTestById(testId);
        return ResponseEntity.ok(test);
    }

    @GetMapping("/questions/{testId}")
    public ResponseEntity<List<Question>> showQuestionsByTestId(@PathVariable long testId) {
        final List<com.studenttest.trspo_test_management.repo.model.Question> questions = questionManagementService.fetchAllQuestionsByTestId(testId);

        List<Question> questionList = questions.stream()
                .map(question -> {
                    List<com.studenttest.trspo_test_management.repo.model.Answer> answers = answerManagementService.fetchAllAnswersByQuestionId(question.getId());

                    List<Answer> answerList = answers.stream()
                            .map(answer -> new Answer(answer.getId(), answer.getQuestionId(), answer.getText(), answer.isCorrect()))
                            .toList();

                    return new Question(question.getId(), question.getTestId(), question.getText(), answerList);
                })
                .toList();

        return ResponseEntity.ok(questionList);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> change(@PathVariable long id, @RequestBody Test test) {
        final String title = test.title();
        final String description = test.description();
        final int timeInMinutes = test.timeInMinutes();
        final List<Question> questions = test.questions();

        try {
            testManagementService.updateTest(id, title, description, timeInMinutes);
            questions.forEach(question -> {
                questionManagementService.updateQuestion(question.id(), question.testId(), question.text());
                question.answers().forEach(answer -> answerManagementService.updateAnswer(answer.id(), answer.questionId(), answer.text(), answer.isCorrect()));
            });

            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable long id) {
        questionManagementService.fetchAllQuestionsByTestId(id)
                .forEach(question -> {
                    answerManagementService.fetchAllAnswersByQuestionId(question.getId())
                        .forEach(answer -> answerManagementService.deleteAnswer(answer.getId()));
                    questionManagementService.deleteQuestion(question.getId());
                });
        testManagementService.deleteTest(id);

        return ResponseEntity.noContent().build();
    }
}
