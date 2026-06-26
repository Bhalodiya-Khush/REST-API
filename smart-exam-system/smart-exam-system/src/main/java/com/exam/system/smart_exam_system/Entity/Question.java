package com.exam.system.smart_exam_system.Entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "questions")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String questionText;

    // Many Questions → One Exam
    @ManyToOne
    @JoinColumn(name = "exam_id", nullable = false)
    private Exam exam;
}