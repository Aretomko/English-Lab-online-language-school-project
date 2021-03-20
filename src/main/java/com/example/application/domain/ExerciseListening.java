package com.example.application.domain;

import javax.persistence.*;
import java.util.*;

@Entity
public class ExerciseListening implements Exercise{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String text;
    private String answers;
    private String rightAnswer;

    @ManyToOne(optional=false)
    @JoinColumn(name="listening_id")
    private Listening listening;

    @OneToMany(mappedBy="exerciseListening", fetch = FetchType.EAGER)
    private Set<AnswerListening> answersListening;

    public ExerciseListening() {
    }

    public ExerciseListening(String text,String answers, String rightAnswer,Listening listening) {
        this.text = text;
        this.answers = answers;
        this.rightAnswer = rightAnswer;
        this.listening = listening;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAnswers() {
        return answers;
    }

    public void setAnswers(String answers) {
        this.answers = answers;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Listening getListening() {
        return listening;
    }

    public void setListening(Listening listening) {
        this.listening = listening;
    }

    public Set<AnswerListening> getAnswersListening() {
        return answersListening;
    }

    public void setAnswersListening(Set<AnswerListening> answersListening) {
        this.answersListening = answersListening;
    }

    public void addAnswer(AnswerListening answerListening){
        if(this.getAnswersListening()==null) this.answersListening= new HashSet<>();
        this.answersListening.add(answerListening);
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    @Override
    public String getType() {
        return "Listening exercise";
    }

    @Override
    public String getAnswersLink() {
        return "admin/answers/questions/listening";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExerciseListening)) return false;
        ExerciseListening that = (ExerciseListening) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, text);
    }
}
