package com.example.application.repos;

import com.example.application.domain.AnswerVocabulary;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerVocabularyRepo extends JpaRepository<AnswerVocabulary, Long> {
}
