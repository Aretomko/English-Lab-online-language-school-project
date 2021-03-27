package com.example.application.service;

import com.example.application.domain.AnswerGrammar;
import com.example.application.domain.ExerciseGrammar;
import com.example.application.repos.AnswerGrammarRepo;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AnswerGrammarService {
    private final AnswerGrammarRepo answerGrammarRepo;

    public AnswerGrammarService(AnswerGrammarRepo answerGrammarRepo) {
        this.answerGrammarRepo = answerGrammarRepo;
    }
    public void save(AnswerGrammar answerGrammar){
        answerGrammarRepo.save(answerGrammar);
    }
    public void delete(AnswerGrammar answerGrammar){
        answerGrammarRepo.delete(answerGrammar);
    }
    public void deleteAll(Set<AnswerGrammar> answersGrammar){
        answerGrammarRepo.deleteAll(answersGrammar);
    }

}
