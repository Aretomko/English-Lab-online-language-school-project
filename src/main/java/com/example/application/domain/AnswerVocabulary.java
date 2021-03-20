package com.example.application.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class AnswerVocabulary implements Answer{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String answer;
    @NotNull
    private String rightAnswer;
    private Boolean isCorrect;

    @ManyToOne(optional=false)
    @JoinColumn(name="exercise_id")
    private ExerciseVocabulary exerciseVocabulary;

    @ManyToOne(optional=false)
    @JoinColumn(name="user_id")
    private User user;

    public AnswerVocabulary() {
    }

    public AnswerVocabulary(String answer,String rightAnswer, boolean isCorrect, ExerciseVocabulary exerciseVocabulary, User user) {
        this.answer = answer;
        this.rightAnswer = rightAnswer;
        this.isCorrect = isCorrect;
        this.exerciseVocabulary = exerciseVocabulary;
        this.user = user;
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

    public ExerciseVocabulary getExerciseVocabulary() {
        return exerciseVocabulary;
    }

    public void setExerciseVocabulary(ExerciseVocabulary exerciseVocabulary) {
        this.exerciseVocabulary = exerciseVocabulary;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
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
        if (!(o instanceof AnswerVocabulary)) return false;
        AnswerVocabulary that = (AnswerVocabulary) o;
        return Objects.equals(id, that.id);
    }

}
