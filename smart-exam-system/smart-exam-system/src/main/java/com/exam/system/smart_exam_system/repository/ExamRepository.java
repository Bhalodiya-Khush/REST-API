package com.exam.system.smart_exam_system.repository;
import com.exam.system.smart_exam_system.Entity.User;
import com.exam.system.smart_exam_system.Entity.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExamRepository extends JpaRepository<Exam, Long> {

    List<Exam> findByTeacher(User teacher);

}