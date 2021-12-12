package com.studenttest.trspo_test_management.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.studenttest.trspo_test_management.repo.model.Test;

public interface TestRepo extends JpaRepository<Test, Long> {
}
