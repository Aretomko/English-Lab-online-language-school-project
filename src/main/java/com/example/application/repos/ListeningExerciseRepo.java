package com.example.application.repos;

import com.example.application.domain.ExerciseListening;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListeningExerciseRepo extends JpaRepository<ExerciseListening, Long> {
}
