package com.exam.system.smart_exam_system.Entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(
        name = "attempts",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"student_id", "exam_id"})
        }
)
public class Attempt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int score;
    private int totalMarks;

    // Many Attempt -> one student
    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private User student;

    // Many Attempt -> one exam
    @ManyToOne
    @JoinColumn(name = "exam_id", nullable = false)
    private Exam exam;
}