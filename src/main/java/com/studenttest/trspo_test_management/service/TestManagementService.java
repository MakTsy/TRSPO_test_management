package com.studenttest.trspo_test_management.service;

import java.util.List;

import com.studenttest.trspo_test_management.repo.model.Test;

public interface TestManagementService {
    List<Test> fetchAllTests();
    Test fetchTestById(long id) throws IllegalArgumentException;
    long createTest(String title, String description, int timeInMinutes);
    void updateTest(long id, String title, String description, Integer timeInMinutes) throws IllegalArgumentException;
    void deleteTest(long id);
}
