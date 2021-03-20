package com.example.application.repos;

import com.example.application.domain.ExerciseVocabulary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VocabularyExerciseRepo extends JpaRepository<ExerciseVocabulary, Long> {
}
