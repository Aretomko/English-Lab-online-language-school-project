package com.example.application.service;

import com.example.application.domain.ExerciseVocabulary;
import com.example.application.repos.VocabularyExerciseRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VocabularyExerciseService {
    private final VocabularyExerciseRepo vocabularyExerciseRepo;

    public VocabularyExerciseService(VocabularyExerciseRepo vocabularyExerciseRepo) {
        this.vocabularyExerciseRepo = vocabularyExerciseRepo;
    }

    public void save(ExerciseVocabulary exerciseVocabulary){
        vocabularyExerciseRepo.save(exerciseVocabulary);
    }
    public void delete(ExerciseVocabulary exerciseVocabulary){
        vocabularyExerciseRepo.delete(exerciseVocabulary);
    }
    public List<ExerciseVocabulary> getAllVocabularyExercises(){
        return vocabularyExerciseRepo.findAll();
    }
    public ExerciseVocabulary getById(String id){
        long longId = Long.parseLong(id.trim());
        return vocabularyExerciseRepo.findById(longId).orElse(null);
    }
}

