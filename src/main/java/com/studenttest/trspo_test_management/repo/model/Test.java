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
@Table(name = "test")
@NoArgsConstructor
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;
    private String title;
    private String description;
    private int timeInMinutes;
    private double rating;

    public Test(String title, String description, int timeInMinutes) {
        this.title = title;
        this.description = description;
        this.timeInMinutes = timeInMinutes;
    }
}
