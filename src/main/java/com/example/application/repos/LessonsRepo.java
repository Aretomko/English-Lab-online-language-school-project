package com.example.application.repos;

import com.example.application.domain.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LessonsRepo extends JpaRepository<Lesson, Long> {
    Lesson findByName(String name);
    Optional<Lesson> findById(Long id);
}
