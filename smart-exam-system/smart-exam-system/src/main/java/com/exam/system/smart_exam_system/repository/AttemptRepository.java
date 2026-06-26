package com.exam.system.smart_exam_system.repository;
import com.exam.system.smart_exam_system.Entity.Attempt;
import com.exam.system.smart_exam_system.Entity.User;
import com.exam.system.smart_exam_system.Entity.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AttemptRepository extends JpaRepository<Attempt, Long> {
    boolean existsByStudentAndExam(User student, Exam exam);
    Optional<Attempt> findByStudentAndExam(User student, Exam exam);
    List<Attempt> findByStudent(User student);
    List<Attempt> findByExam(Exam exam);
}