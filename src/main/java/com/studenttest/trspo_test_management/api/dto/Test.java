package com.studenttest.trspo_test_management.api.dto;

import java.util.List;

public record Test(String title, String description, int timeInMinutes, List<Question> questions) {
}
