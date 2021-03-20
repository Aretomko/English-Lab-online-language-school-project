package com.example.application.domain;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class AnswerGrammar implements Answer{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String answer;

    private Boolean isCorrect;

    @ManyToOne(optional=false)
    @JoinColumn(name="exercise_grammar_id")
    private ExerciseGrammar exerciseGrammar;

    @ManyToOne(optional=false)
    @JoinColumn(name="user_id")
    private User user;

    public AnswerGrammar() {
    }

    public AnswerGrammar(String answer, ExerciseGrammar exerciseGrammar, User user, boolean isCorrect) {
        this.answer = answer;
        this.exerciseGrammar = exerciseGrammar;
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

    public ExerciseGrammar getExerciseGrammar() {
        return exerciseGrammar;
    }

    public void setExerciseGrammar(ExerciseGrammar exerciseGrammar) {
        this.exerciseGrammar = exerciseGrammar;
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
        if (!(o instanceof AnswerGrammar)) return false;
        AnswerGrammar that = (AnswerGrammar) o;
        return Objects.equals(id, that.id);
    }

}
