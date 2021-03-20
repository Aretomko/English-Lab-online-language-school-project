package com.example.application.repos;

import com.example.application.domain.ExerciseReading;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReadingExerciseRepo extends JpaRepository<ExerciseReading, Long> {
}
