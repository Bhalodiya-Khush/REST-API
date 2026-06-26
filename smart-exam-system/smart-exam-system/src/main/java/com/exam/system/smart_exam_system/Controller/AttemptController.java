package com.exam.system.smart_exam_system.Controller;

import com.exam.system.smart_exam_system.Entity.Attempt;
import com.exam.system.smart_exam_system.Service.AttemptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/attempts")
public class AttemptController {

    @Autowired
    private AttemptService attemptService;

    // submit student Answer
    @PostMapping("/submit")
    public ResponseEntity<Attempt> submitAnswers(
            @RequestParam Long studentId,
            @RequestParam Long examId,
            @RequestBody List<Map<String, Long>> answers) {

        Attempt attempt = attemptService.submitAnswers(studentId, examId, answers);
        return ResponseEntity.ok(attempt);
    }

    // Get Attempt by student & exam
    @GetMapping
    public ResponseEntity<Attempt> getAttempt(
            @RequestParam Long studentId,
            @RequestParam Long examId) {

        return ResponseEntity.ok(
                attemptService.getAttempt(studentId, examId)
        );
    }

    //  Get all attempts of a student
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<Attempt>> getAttemptsByStudent(@PathVariable Long studentId) {

        return ResponseEntity.ok(
                attemptService.getAttemptsByStudent(studentId)
        );
    }

    // Get all attempts for an exam
    @GetMapping("/exam/{examId}")
    public ResponseEntity<List<Attempt>> getAttemptsByExam(@PathVariable Long examId) {

        return ResponseEntity.ok(
                attemptService.getAttemptsByExam(examId)
        );
    }
}