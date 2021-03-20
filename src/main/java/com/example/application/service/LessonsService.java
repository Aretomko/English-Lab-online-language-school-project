package com.example.application.service;

import com.example.application.domain.*;
import com.example.application.repos.LessonsRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LessonsService {
    private final LessonsRepo lessonsRepo;

    public LessonsService(LessonsRepo lessonsRepo) {
        this.lessonsRepo = lessonsRepo;
    }
    public void save(Lesson lesson){
        lessonsRepo.save(lesson);
    }
    public List<Lesson> getAllLessons(){
        return lessonsRepo.findAll();
    }
    public String getStringName(Lesson lesson){
        return lesson.getName();
    }
    public Lesson getLessonByName(String name){
        return lessonsRepo.findByName(name);
    }
    public void delete(Lesson lesson){
        lessonsRepo.delete(lesson);
    }
    public List<String> getAllLessonsNames(){
        return  lessonsRepo.findAll().stream().map(Lesson::getName).collect(Collectors.toList());
    }
    public String getStringNameIdByReading(Reading reading){
        if(reading.getLesson()==null) return "not set"
                ;
        return reading.getLesson().getName() +" id " +reading.getLesson().getId();
    }
    public String getStringNameIdByListening(Listening listening){
        if(listening.getLesson()==null) return "not set"
                ;
        return listening.getLesson().getName()+" id "+ listening.getLesson().getId();
    }
    public String getStringNameIdByGrammarExercise(ExerciseGrammar exerciseGrammar){
        if(exerciseGrammar.getLesson()==null) return "not set"
                ;
        return exerciseGrammar.getLesson().getName()+" id "+exerciseGrammar.getLesson().getId();
    }
    public String getStringNameIdByVocabularyExercise(ExerciseVocabulary exerciseVocabulary){
        if (exerciseVocabulary.getLesson()==null) return "not set";
        return exerciseVocabulary.getLesson().getName()+" id "+exerciseVocabulary.getLesson().getId();
    }
    public Lesson findLessonById(String id){
        Long idLong = Long.valueOf(id.trim());
        Lesson lesson = lessonsRepo.findById(idLong).orElse(null);
        if (lesson == null) throw new RuntimeException("Wrong id provided");
        return lesson;
    }
}
