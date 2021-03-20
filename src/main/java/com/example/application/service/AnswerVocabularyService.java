package com.example.application.service;

import com.example.application.domain.AnswerVocabulary;
import com.example.application.repos.AnswerVocabularyRepo;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AnswerVocabularyService {
    private final AnswerVocabularyRepo answerVocabularyRepo;

    public AnswerVocabularyService(AnswerVocabularyRepo answerVocabularyRepo) {
        this.answerVocabularyRepo = answerVocabularyRepo;
    }
    public void save(AnswerVocabulary answerVocabulary){
        answerVocabularyRepo.save(answerVocabulary);
    }
    public void delete(AnswerVocabulary answerVocabulary){
        answerVocabularyRepo.delete(answerVocabulary);
    }
    public void deleteAll(Set<AnswerVocabulary> answersVocabulary){
        answerVocabularyRepo.deleteAll(answersVocabulary);
    }
}
