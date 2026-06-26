package com.exam.system.smart_exam_system.repository;

import com.exam.system.smart_exam_system.Entity.Question;
import com.exam.system.smart_exam_system.Entity.Exam;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByExam(Exam exam);
}