package com.example.application.domain;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Listening implements Exercise{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private Boolean isHomework;

    @ManyToOne
    @JoinColumn(name="lesson_id")
    private Lesson lesson;

    @OneToMany(mappedBy="listening", fetch = FetchType.EAGER)
    private Set<ExerciseListening> exerciseListening;

    public Listening() {
    }

    public Listening(String name, Lesson lesson, Boolean isHomework) {
        this.name = name;
        this.lesson = lesson;
        this.isHomework = isHomework;
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

    public Set<ExerciseListening> getExerciseListening() {
        return exerciseListening;
    }

    public void setExerciseListening(Set<ExerciseListening> exerciseListening) {
        this.exerciseListening = exerciseListening;
    }

    public Boolean getHomework() {
        return isHomework;
    }

    public void setHomework(Boolean homework) {
        isHomework = homework;
    }

    @Override
    public String getType() {
        return "Listening task";
    }

    @Override
    public String getAnswersLink() {
        return "admin/answers/task/listening";
    }

    @Override
    public String getRightAnswer() {
        return "Is a task";
    }
}
