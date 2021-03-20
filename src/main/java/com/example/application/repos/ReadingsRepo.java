package com.example.application.repos;

import com.example.application.domain.Reading;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReadingsRepo extends JpaRepository<Reading, Long> {
    Optional<Reading> findById(Long id);
}
