package com.example.application.domain;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
public class ExerciseGrammar implements Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String textStart;
    private String textEnd;
    private String answers;
    private String rightAnswer;
    private Boolean isHomework;

    @ManyToOne(optional=false)
    @JoinColumn(name="lesson_id")
    private Lesson lesson;

    @OneToMany(mappedBy="exerciseGrammar", fetch = FetchType.EAGER)
    private Set<AnswerGrammar> answersGrammar;

    public ExerciseGrammar() {

    }

    public ExerciseGrammar(String textStart, String textEnd, String answers, String rightAnswer, Lesson lesson, Boolean isHomework) {
        this.textStart = textStart;
        this.textEnd = textEnd;
        this.answers = answers;
        this.lesson = lesson;
        this.rightAnswer = rightAnswer;
        this.isHomework = isHomework;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public String getTextStart() {
        return textStart;
    }

    public void setTextStart(String textStart) {
        this.textStart = textStart;
    }

    public String getTextEnd() {
        return textEnd;
    }

    public void setTextEnd(String textEnd) {
        this.textEnd = textEnd;
    }

    public String getAnswers() {
        return answers;
    }

    public void setAnswers(String answers) {
        this.answers = answers;
    }

    public Set<AnswerGrammar> getAnswersGrammar() {
        return answersGrammar;
    }

    public void setAnswersGrammar(Set<AnswerGrammar> answersGrammar) {
        this.answersGrammar = answersGrammar;
    }

    public void addAnswer(AnswerGrammar answerGrammar){
        this.answersGrammar.add(answerGrammar);
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public Boolean getHomework() {
        return isHomework;
    }

    public void setHomework(Boolean homework) {
        isHomework = homework;
    }

    @Override
    public String getType() {
        return "Grammar exercise";
    }

    @Override
    public String getAnswersLink() {
        return "admin/answers/questions/grammar";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExerciseGrammar)) return false;
        ExerciseGrammar that = (ExerciseGrammar) o;
        return Objects.equals(id, that.id);
    }
}
