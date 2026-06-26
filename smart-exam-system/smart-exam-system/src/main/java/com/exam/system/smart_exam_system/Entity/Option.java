package com.exam.system.smart_exam_system.Entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "options")
public class Option {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String optionText;
    private boolean isCorrect;

    // Many option -> one question
    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;
}