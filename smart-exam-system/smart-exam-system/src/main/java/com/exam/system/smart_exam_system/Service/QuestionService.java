package com.exam.system.smart_exam_system.Service;
import com.exam.system.smart_exam_system.Entity.*;
import com.exam.system.smart_exam_system.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private ExamRepository examRepository;

    // Create a question for an exam
    public Question createQuestion(Long examId, String questionText) {

        Optional<Exam> optionalExam = examRepository.findById(examId);

        if (optionalExam.isPresent()) {

            Question question = new Question();
            question.setExam(optionalExam.get());
            question.setQuestionText(questionText);
            if (questionText == null || questionText.isEmpty()) {
                throw new RuntimeException("Question text cannot be empty");
            }
            return questionRepository.save(question);

        } else {
            throw new RuntimeException("Exam not found");
        }
    }

    // Get question by ID
    public Question getQuestionById(Long questionId) {
        return questionRepository.findById(questionId)
                .orElseThrow(() -> new RuntimeException("Question not found"));
    }

    // Get all questions for an exam
    public List<Question> getQuestionsByExam(Long examId) {

        Optional<Exam> optionalExam = examRepository.findById(examId);

        if (optionalExam.isPresent()) {
            return questionRepository.findByExam(optionalExam.get());
        } else {
            throw new RuntimeException("Exam not found");
        }
    }

    // Update a question
    public Question updateQuestion(Long questionId, String questionText) {

        Optional<Question> optionalQuestion = questionRepository.findById(questionId);

        if (optionalQuestion.isPresent()) {
            Question question = optionalQuestion.get();
            if (questionText != null) question.setQuestionText(questionText);
            return questionRepository.save(question);
        } else {
            throw new RuntimeException("Question not found");
        }
    }

    // Delete a question
    public void deleteQuestion(Long questionId) {

        Optional<Question> optionalQuestion = questionRepository.findById(questionId);

        if (optionalQuestion.isPresent()) {
            questionRepository.delete(optionalQuestion.get());
        } else {
            throw new RuntimeException("Question not found");
        }
    }


}