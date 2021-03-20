package com.example.application.repos;

import com.example.application.domain.AnswerReading;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerReadingRepo extends JpaRepository<AnswerReading,Long> {
}
