package com.exam.system.smart_exam_system.Service;
import com.exam.system.smart_exam_system.Entity.User;
import com.exam.system.smart_exam_system.Entity.Exam;
import com.exam.system.smart_exam_system.repository.UserRepository;
import com.exam.system.smart_exam_system.repository.ExamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExamService {

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private UserRepository userRepository;

    //  Create Exam (Teacher)
    public Exam createExam(Long teacherId, Exam exam) {

        Optional<User> teacherOptional = userRepository.findById(teacherId);
        if (teacherOptional.isPresent()) {
            User teacher = teacherOptional.get();
            if (!teacher.getRole().name().equals("ROLE_TEACHER")) {
                throw new RuntimeException("Only teachers can create exams");
            }
            exam.setTeacher(teacher);
            return examRepository.save(exam);
        } else {
            throw new RuntimeException("Teacher not found");
        }
    }

    //  Get all exams
    public List<Exam> getAllExams() {
        return examRepository.findAll();
    }

    //  Get exam by ID
    public Exam getExamById(Long examId) {

        Optional<Exam> examOptional = examRepository.findById(examId);

        if (examOptional.isPresent()) {
            return examOptional.get();
        } else {
            throw new RuntimeException("Exam not found");
        }
    }

    //  Get exams by teacher
    public List<Exam> getExamsByTeacher(Long teacherId) {

        Optional<User> optionalTeacher = userRepository.findById(teacherId);

        if (optionalTeacher.isPresent()) {
            User teacher = optionalTeacher.get();
            return examRepository.findByTeacher(teacher);
        } else {
            throw new RuntimeException("Teacher not found");
        }
    }
}