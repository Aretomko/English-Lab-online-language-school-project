package com.example.application.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class AnswerListening implements Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String answer;

    private Boolean isCorrect;

    @ManyToOne(optional=false)
    @JoinColumn(name="exercise_id")
    private ExerciseListening exerciseListening;

    @ManyToOne(optional=false)
    @JoinColumn(name="user_id")
    private User user;

    public AnswerListening() {
    }

    public AnswerListening(String answer,ExerciseListening exerciseListening, User user, boolean isCorrect) {
        this.answer = answer;
        this.exerciseListening = exerciseListening;
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

    public ExerciseListening getExerciseListening() {
        return exerciseListening;
    }

    public void setExerciseListening(ExerciseListening exerciseListening) {
        this.exerciseListening = exerciseListening;
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
        if (!(o instanceof AnswerListening)) return false;
        AnswerListening that = (AnswerListening) o;
        return Objects.equals(id, that.id);
    }

}
