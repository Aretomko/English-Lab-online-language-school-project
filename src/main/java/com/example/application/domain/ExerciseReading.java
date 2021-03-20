package com.example.application.domain;

import javax.persistence.*;
import java.util.*;

@Entity
public class ExerciseReading implements Exercise{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String text;
    private String answers;
    private String rightAnswer;

    @ManyToOne(optional=false)
    @JoinColumn(name="reading_id")
    private Reading reading;

    @OneToMany(mappedBy="exerciseReading", fetch = FetchType.EAGER)
    private Set<AnswerReading> answersReading;


    public ExerciseReading() {
    }

    public ExerciseReading(String text, String answers,String rightAnswer, Reading reading) {
        this.text = text;
        this.answers = answers;
        this.rightAnswer = rightAnswer;
        this.reading = reading;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAnswers() {
        return answers;
    }

    public void setAnswers(String answers) {
        this.answers = answers;
    }

    public Reading getReading() {
        return reading;
    }

    public void setReading(Reading reading) {
        this.reading = reading;
    }

    public Set<AnswerReading> getAnswersReading() {
        return answersReading;
    }

    public void setAnswersReading(Set<AnswerReading> answersReading) {
        this.answersReading = answersReading;
    }

    public void addAnswerReading(AnswerReading answerReading){
        this.answersReading.add(answerReading);
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }



    @Override
    public String getType() {
        return "Reading exercise";
    }

    @Override
    public String getAnswersLink() {
        return "admin/answers/questions/reading";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExerciseReading)) return false;
        ExerciseReading that = (ExerciseReading) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text);
    }
}
