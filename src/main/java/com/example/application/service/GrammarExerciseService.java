package com.example.application.service;

import com.example.application.domain.ExerciseGrammar;
import com.example.application.repos.GrammarExerciseRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GrammarExerciseService {
    private final GrammarExerciseRepo grammarExerciseRepo;

    public GrammarExerciseService(GrammarExerciseRepo grammarExerciseRepo) {
        this.grammarExerciseRepo = grammarExerciseRepo;
    }
    public void save(ExerciseGrammar exerciseGrammar){
        grammarExerciseRepo.save(exerciseGrammar);
    }
    public void delete(ExerciseGrammar exerciseGrammar){
        grammarExerciseRepo.delete(exerciseGrammar);
    }

    public List<ExerciseGrammar> getAllGrammarExercises(){
        return grammarExerciseRepo.findAll();
    }

    public ExerciseGrammar getById(String id){
        Long longId = Long.valueOf(id.trim());
        ExerciseGrammar exerciseGrammar = grammarExerciseRepo.findById(longId).orElse(null);
        return exerciseGrammar;
    }
}
