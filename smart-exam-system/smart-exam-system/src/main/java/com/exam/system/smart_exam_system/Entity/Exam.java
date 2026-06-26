package com.exam.system.smart_exam_system.Entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "exams")
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private int duration;

    // many exam -> one teacher
    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    private User teacher;
}