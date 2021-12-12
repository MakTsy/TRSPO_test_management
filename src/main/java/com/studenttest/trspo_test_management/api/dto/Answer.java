package com.studenttest.trspo_test_management.api.dto;

public record Answer(long id, long questionId, String text, boolean isCorrect) {
}
