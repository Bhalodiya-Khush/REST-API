package com.exam.system.smart_exam_system.Controller;

import com.exam.system.smart_exam_system.Entity.Exam;
import com.exam.system.smart_exam_system.Service.ExamService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/exams")
public class ExamController {

    @Autowired
    private ExamService examService;

    // Create Exam (Teacher)
    @PostMapping("/teacher/{teacherId}")
    public ResponseEntity<Exam> createExam(
            @PathVariable Long teacherId,
            @RequestBody Exam exam) {

        Exam createdExam = examService.createExam(teacherId, exam);
        return ResponseEntity.ok(createdExam);
    }

    // Get All Exams
    @GetMapping
    public ResponseEntity<List<Exam>> getAllExams() {
        return ResponseEntity.ok(examService.getAllExams());
    }

    // Get Exam by ID
    @GetMapping("/{examId}")
    public ResponseEntity<Exam> getExamById(@PathVariable Long examId) {
        return ResponseEntity.ok(examService.getExamById(examId));
    }

    // Get Exams by Teacher
    @GetMapping("/teacher/{teacherId}")
    public ResponseEntity<List<Exam>> getExamsByTeacher(@PathVariable Long teacherId) {
        return ResponseEntity.ok(examService.getExamsByTeacher(teacherId));
    }
}