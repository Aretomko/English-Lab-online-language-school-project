package com.example.application.service;

import com.example.application.domain.ExerciseReading;
import com.example.application.repos.ReadingExerciseRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReadingExercisesService{
    private final ReadingExerciseRepo readingExerciseRepo;

    public ReadingExercisesService(ReadingExerciseRepo readingExerciseRepo) {
        this.readingExerciseRepo = readingExerciseRepo;
    }
    public void save(ExerciseReading exerciseReading){
        readingExerciseRepo.save(exerciseReading);
    }
    public void delete(ExerciseReading exerciseReading){
        readingExerciseRepo.delete(exerciseReading);
    }
    public List<ExerciseReading> getAllReadingExercises(){
        return readingExerciseRepo.findAll();
    }

    public ExerciseReading getExerciseReadingById(String id){
        Long longId  = Long.valueOf(id.trim());
        return readingExerciseRepo.findById(longId).orElse(null);
    }
}
