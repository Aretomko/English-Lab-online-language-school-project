package com.example.application.service;

import com.example.application.domain.Answer;
import com.example.application.domain.AnswerListening;
import com.example.application.domain.ExerciseListening;
import com.example.application.repos.AnswerListeningRepo;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AnswersListeningService {
    private final AnswerListeningRepo answerListeningRepo;

    public AnswersListeningService(AnswerListeningRepo answerListeningRepo) {
        this.answerListeningRepo = answerListeningRepo;
    }
    public void save(AnswerListening answerListening){
        answerListeningRepo.save(answerListening);
    }
    public void delete(AnswerListening answerListening){
        answerListeningRepo.delete(answerListening);
    }
    public void deleteAll(Set<AnswerListening> answersListening){
        answerListeningRepo.deleteAll(answersListening);
    }
}
