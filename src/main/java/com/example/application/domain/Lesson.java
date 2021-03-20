package com.example.application.domain;


import javax.persistence.*;

import java.util.Set;
import java.util.List;



@Entity
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String description;
    private String filesLink;
    private String meetingLink;

    @ManyToOne
    @JoinColumn(name="course_id")
    private Course course;
    @OneToMany(mappedBy="lesson", fetch= FetchType.EAGER)
    private Set<ExerciseGrammar> exercisesGrammar;
    @OneToMany(mappedBy="lesson", fetch = FetchType.EAGER)
    private Set<Reading> reading;
    @OneToMany(mappedBy="lesson" , fetch = FetchType.EAGER)
    private Set<ExerciseVocabulary> exercisesVocabulary;
    @OneToMany(mappedBy="lesson", fetch = FetchType.EAGER)
    private Set<Listening> listening;

    public Lesson() {
    }

    public Lesson(String name, String description, String filesLink, Course course, String meetingLink) {
        this.name = name;
        this.description = description;
        this.filesLink = filesLink;
        this.course = course;
        this.meetingLink = meetingLink;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFilesLink() {
        return filesLink;
    }

    public void setFilesLink(String filesLink) {
        this.filesLink = filesLink;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }


    public Set<ExerciseGrammar> getExercisesGrammar() {
        return exercisesGrammar;
    }

    public void setExercisesGrammar(Set<ExerciseGrammar> exercisesGrammar) {
        this.exercisesGrammar = exercisesGrammar;
    }

    public Set<ExerciseVocabulary> getExercisesVocabulary() {
        return exercisesVocabulary;
    }

    public void setExercisesVocabulary(Set<ExerciseVocabulary> exercisesVocabulary) {
        this.exercisesVocabulary = exercisesVocabulary;
    }

    public Set<Reading> getReading() {
        return reading;
    }

    public void setReading(Set<Reading> reading) {
        this.reading = reading;
    }

    public Set<Listening> getListening() {
        return listening;
    }

    public void setListening(Set<Listening> listening) {
        this.listening = listening;
    }

    public String getMeetingLink() {
        return meetingLink;
    }

    public void setMeetingLink(String meetingLink) {
        this.meetingLink = meetingLink;
    }
}
