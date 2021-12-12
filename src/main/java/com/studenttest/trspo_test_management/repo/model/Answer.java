package com.studenttest.trspo_test_management.repo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = "answer")
@NoArgsConstructor
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    private long questionId;
    private String text;
    private boolean isCorrect;

    public Answer(long questionId, String text, boolean isCorrect) {
        this.questionId = questionId;
        this.text = text;
        this.isCorrect = isCorrect;
    }
}
