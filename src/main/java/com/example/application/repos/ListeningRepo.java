package com.example.application.repos;

import com.example.application.domain.Lesson;
import com.example.application.domain.Listening;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ListeningRepo extends JpaRepository<Listening, Long> {
    Optional<Listening> findById(Long id);
}
