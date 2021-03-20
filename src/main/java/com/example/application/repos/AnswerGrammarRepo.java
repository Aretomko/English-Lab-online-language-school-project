package com.example.application.repos;

import com.example.application.domain.AnswerGrammar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerGrammarRepo extends JpaRepository<AnswerGrammar, Long> {
}
