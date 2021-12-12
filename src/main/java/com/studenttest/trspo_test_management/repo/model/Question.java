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
@Table(name = "question")
@NoArgsConstructor
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    private long testId;
    private String text;

    public Question(long testId, String text) {
        this.testId = testId;
        this.text = text;
    }
}
