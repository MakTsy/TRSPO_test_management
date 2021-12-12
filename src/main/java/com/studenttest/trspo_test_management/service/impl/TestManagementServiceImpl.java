package com.studenttest.trspo_test_management.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.studenttest.trspo_test_management.repo.TestRepo;
import com.studenttest.trspo_test_management.repo.model.Test;
import com.studenttest.trspo_test_management.service.TestManagementService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class TestManagementServiceImpl implements TestManagementService {

    private final TestRepo testRepo;

    public List<Test> fetchAllTests() {
        return testRepo.findAll();
    }

    public Test fetchTestById(long id) throws IllegalArgumentException {
        final Optional<Test> maybeTest = testRepo.findById(id);

        if(maybeTest.isPresent())
            return maybeTest.get();
        else
            throw new IllegalArgumentException("Invalid test ID");
    }

    public long createTest(String title, String description, int timeInMinutes) {
        final Test test = new Test(title, description, timeInMinutes);
        final Test savedTest = testRepo.save(test);

        return savedTest.getId();
    }

    public void updateTest(long id, String title, String description, Integer timeInMinutes) throws IllegalArgumentException {
        final Optional<Test> maybeTest = testRepo.findById(id);

        if(maybeTest.isEmpty())
            throw new IllegalArgumentException("Invalid user ID");

        final Test test = maybeTest.get();
        if (title != null && !title.isBlank()) test.setTitle(title);
        if (description != null && !description.isBlank()) test.setDescription(description);
        if (timeInMinutes != null) test.setTimeInMinutes(timeInMinutes);
        testRepo.save(test);
    }

    public void deleteTest(long id) {
        testRepo.deleteById(id);
    }


}
