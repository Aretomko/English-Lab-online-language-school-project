package com.example.application.service;

import com.example.application.domain.AnswerReading;
import com.example.application.repos.AnswerReadingRepo;
import org.springframework.stereotype.Service;

@Service
public class AnswerReadingService {
    private final AnswerReadingRepo answerReadingRepo;

    public AnswerReadingService(AnswerReadingRepo answerReadingRepo) {
        this.answerReadingRepo = answerReadingRepo;
    }

    public void save(AnswerReading answerReading){
        answerReadingRepo.save(answerReading);
    }
    public void delete(AnswerReading answerReading){
        answerReadingRepo.delete(answerReading);
    }
}
