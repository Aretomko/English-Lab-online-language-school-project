package com.example.application.domain;


import javax.persistence.*;
import java.util.Set;

@Entity
public class Reading implements Exercise {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String text;

    private Boolean isHomework;

    @ManyToOne
    @JoinColumn(name="lesson_id")
    private Lesson lesson;

    @OneToMany(mappedBy="reading", fetch = FetchType.EAGER)
    private Set<ExerciseReading> exercisesReading;

    public Reading() {
    }

    public Reading(String name, Lesson lesson) {
        this.name = name;
        this.lesson = lesson;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Set<ExerciseReading> getExercisesReading() {
        return exercisesReading;
    }

    public void setExercisesReading(Set<ExerciseReading> exercisesReading) {
        this.exercisesReading = exercisesReading;
    }

    public Boolean getHomework() {
        return isHomework;
    }

    public void setHomework(Boolean homework) {
        isHomework = homework;
    }

    @Override
    public String getType() {
        return "Reading task";
    }

    @Override
    public String getAnswersLink() {
        return "admin/answers/task/reading";
    }

    @Override
    public String getRightAnswer() {
        return "Is a task";
    }
}
