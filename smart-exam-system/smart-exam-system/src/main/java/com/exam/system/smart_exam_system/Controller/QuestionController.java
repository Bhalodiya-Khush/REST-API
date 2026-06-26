package com.exam.system.smart_exam_system.Controller;

import com.exam.system.smart_exam_system.Entity.*;
import com.exam.system.smart_exam_system.Service.QuestionService;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questions")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    // Create Question for Exam
    @PostMapping("/exam/{examId}")
    public ResponseEntity<Question> createQuestion(
            @PathVariable Long examId,
            @RequestBody Question request) {

        Question question = questionService.createQuestion(
                examId,
                request.getQuestionText()
        );

        return ResponseEntity.ok(question);
    }

    // Get Question by ID
    @GetMapping("/{questionId}")
    public ResponseEntity<Question> getQuestionById(@PathVariable Long questionId) {

        return ResponseEntity.ok(
                questionService.getQuestionById(questionId)
        );
    }

    // Get all Questions for an Exam
    @GetMapping("/exam/{examId}")
    public ResponseEntity<List<Question>> getQuestionsByExam(@PathVariable Long examId) {

        return ResponseEntity.ok(
                questionService.getQuestionsByExam(examId)
        );
    }

    // Update Question
    @PutMapping("/{questionId}")
    public ResponseEntity<Question> updateQuestion(
            @PathVariable Long questionId,
            @RequestBody Question request) {

        Question updatedQuestion = questionService.updateQuestion(
                questionId,
                request.getQuestionText()
        );

        return ResponseEntity.ok(updatedQuestion);
    }

    // Delete Question
    @DeleteMapping("/{questionId}")
    public ResponseEntity<String> deleteQuestion(@PathVariable Long questionId) {

        questionService.deleteQuestion(questionId);
        return ResponseEntity.ok("Question deleted successfully");
    }
}