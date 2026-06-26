package com.exam.system.smart_exam_system.repository;
import com.exam.system.smart_exam_system.Entity.Question;
import com.exam.system.smart_exam_system.Entity.Option;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OptionRepository extends JpaRepository<Option, Long> {
    List<Option> findByQuestion(Question question);
    List<Option> findByQuestionId(Long questionId);
    boolean existsByQuestionIdAndOptionTextIgnoreCase(Long questionId, String optionText);
    boolean existsByQuestionIdAndIsCorrectTrue(Long questionId);
}