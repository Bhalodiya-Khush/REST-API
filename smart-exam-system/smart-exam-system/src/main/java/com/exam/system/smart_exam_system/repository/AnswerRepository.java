package com.exam.system.smart_exam_system.repository;

import com.exam.system.smart_exam_system.Entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
}