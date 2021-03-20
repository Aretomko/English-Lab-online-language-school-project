package com.example.application.domain;

import com.sun.el.parser.BooleanNode;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class AnswerReading implements Answer{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String answer;

    @ManyToOne(optional=false)
    @JoinColumn(name="exercise_id")
    private ExerciseReading exerciseReading;

    private Boolean isCorrect;

    @ManyToOne(optional=false)
    @JoinColumn(name="user_id")
    private User user;

    public AnswerReading() {
    }

    public AnswerReading(String answer, ExerciseReading exerciseReading, User user, boolean isCorrect) {
        this.answer = answer;
        this.exerciseReading = exerciseReading;
        this.user = user;
        this.isCorrect = isCorrect;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public ExerciseReading getExerciseReading() {
        return exerciseReading;
    }

    public void setExerciseReading(ExerciseReading exerciseReading) {
        this.exerciseReading = exerciseReading;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Boolean getCorrect() {
        return isCorrect;
    }

    public void setCorrect(Boolean correct) {
        isCorrect = correct;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AnswerReading)) return false;
        AnswerReading that = (AnswerReading) o;
        return Objects.equals(id, that.id);
    }
}


