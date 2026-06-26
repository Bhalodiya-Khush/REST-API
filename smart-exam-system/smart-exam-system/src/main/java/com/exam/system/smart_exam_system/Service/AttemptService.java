package com.exam.system.smart_exam_system.Service;
import com.exam.system.smart_exam_system.Entity.*;
import com.exam.system.smart_exam_system.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AttemptService {

    @Autowired
    private AttemptRepository attemptRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ExamRepository examRepository;

    @Autowired
    private OptionRepository optionRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository answerRepository;

    // Create Attempt (only one per student per exam)
    public Attempt submitAnswers(Long studentId, Long examId, List<Map<String, Long>> answers) {

        User student = userRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Exam exam = examRepository.findById(examId)
                .orElseThrow(() -> new RuntimeException("Exam not found"));

        if (attemptRepository.existsByStudentAndExam(student, exam)) {
            throw new RuntimeException("Already attempted");
        }

        Attempt attempt = new Attempt();
        attempt.setStudent(student);
        attempt.setExam(exam);

        attempt = attemptRepository.save(attempt);

        int score = 0;
        int totalMarks = answers.size();

        for (Map<String, Long> ans : answers) {

            Long questionId = ans.get("questionId");
            Long optionId = ans.get("optionId");

            Question question = questionRepository.findById(questionId)
                    .orElseThrow(() -> new RuntimeException("Question not found"));

            Option option = optionRepository.findById(optionId)
                    .orElseThrow(() -> new RuntimeException("Option not found"));

            Answer answer = new Answer();
            answer.setAttempt(attempt);
            answer.setQuestion(question);
            answer.setSelectedOption(option);

            answerRepository.save(answer);

            if (option.isCorrect()) {
                score++;
            }
        }

        attempt.setScore(score);
        attempt.setTotalMarks(totalMarks);

        return attemptRepository.save(attempt);
    }

    // Get Attempt by student & exam
    public Attempt getAttempt(Long studentId, Long examId) {

        Optional<User> optionalStudent = userRepository.findById(studentId);
        Optional<Exam> optionalExam = examRepository.findById(examId);

        if (optionalStudent.isPresent() && optionalExam.isPresent()) {

            Optional<Attempt> optionalAttempt =
                    attemptRepository.findByStudentAndExam(
                            optionalStudent.get(),
                            optionalExam.get()
                    );

            if (optionalAttempt.isPresent()) {
                return optionalAttempt.get();
            } else {
                throw new RuntimeException("Attempt not found");
            }

        } else {
            throw new RuntimeException("Student or Exam not found");
        }
    }

    // Get all attempts of a student
    public List<Attempt> getAttemptsByStudent(Long studentId) {

        Optional<User> optionalStudent = userRepository.findById(studentId);

        if (optionalStudent.isPresent()) {
            return attemptRepository.findByStudent(optionalStudent.get());
        } else {
            throw new RuntimeException("Student not found");
        }
    }

    // Get all attempts for an exam
    public List<Attempt> getAttemptsByExam(Long examId) {

        Optional<Exam> optionalExam = examRepository.findById(examId);

        if (optionalExam.isPresent()) {
            return attemptRepository.findByExam(optionalExam.get());
        } else {
            throw new RuntimeException("Exam not found");
        }
    }
}