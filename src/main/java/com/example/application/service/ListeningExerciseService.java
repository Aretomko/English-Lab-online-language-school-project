package com.example.application.service;

import com.example.application.domain.ExerciseListening;
import com.example.application.repos.ListeningExerciseRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListeningExerciseService {
    private final ListeningExerciseRepo listeningExerciseRepo;

    public ListeningExerciseService(ListeningExerciseRepo listeningExerciseRepo) {
        this.listeningExerciseRepo = listeningExerciseRepo;
    }
    public void save(ExerciseListening exerciseListening){
        listeningExerciseRepo.save(exerciseListening);
    }
    public void delete(ExerciseListening exerciseListening){
        listeningExerciseRepo.delete(exerciseListening);
    }
    public List<ExerciseListening> getAllListeningExercises(){
        return listeningExerciseRepo.findAll();
    }
}
