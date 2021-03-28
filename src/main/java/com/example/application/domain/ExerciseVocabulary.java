package com.example.application.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
public class ExerciseVocabulary implements Exercise{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String text;
    @NotNull
    private String answer;
    @NotNull
    private String rightAnswer;
    private Boolean isHomework;

    @ManyToOne(optional=false)
    @JoinColumn(name="lesson_id")
    private Lesson lesson;

    @OneToMany(mappedBy="exerciseVocabulary", fetch = FetchType.EAGER)
    private Set<AnswerVocabulary> answers;


    public ExerciseVocabulary() {
    }

    public ExerciseVocabulary(String text, String answer, String rightAnswer, Lesson lesson, Boolean isHomework) {
        this.text = text;
        this.answer = answer;
        this.rightAnswer = rightAnswer;
        this.lesson = lesson;
        this.isHomework = isHomework;
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

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public Set<AnswerVocabulary> getAnswers() {
        return answers;
    }

    public void setAnswers(Set<AnswerVocabulary> answers) {
        this.answers = answers;
    }

    public Boolean getHomework() {
        return isHomework;
    }

    public void setHomework(Boolean homework) {
        isHomework = homework;
    }

    @Override
    public String getType() {
        return "Vocabulary exercise";
    }

    @Override
    public String getAnswersLink() {
        return "admin/answers/questions/vocabulary";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExerciseVocabulary)) return false;
        ExerciseVocabulary that = (ExerciseVocabulary) o;
        return Objects.equals(id, that.id);
    }
    public void addAnswerVocabulary(AnswerVocabulary answerVocabulary){
        this.answers.add(answerVocabulary);
    }
}
