package com.example.application.repos;

import com.example.application.domain.ExerciseGrammar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GrammarExerciseRepo extends JpaRepository<ExerciseGrammar, Long> {
}
