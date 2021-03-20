package com.example.application.service;

import com.example.application.domain.ExerciseReading;
import com.example.application.domain.Reading;
import com.example.application.repos.ReadingsRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReadingService {
    private final ReadingsRepo readingsRepo;

    public ReadingService(ReadingsRepo readingsRepo) {
        this.readingsRepo = readingsRepo;
    }

    public List<Reading> getAllReadings(){
        return readingsRepo.findAll();
    }
    public void delete(Reading reading){
        readingsRepo.delete(reading);
    }
    public void save(Reading reading){
        readingsRepo.save(reading);
    }

    public Reading findReadingById(String id){
        Long idLong = Long.valueOf(id.trim());
        Reading reading = readingsRepo.findById(idLong).orElse(null);
        if (reading == null) throw new RuntimeException("Wrong id provided");
        return reading;
    }
    public String getStringNameIdByReadingExercise(ExerciseReading exerciseReading){
        if(exerciseReading.getReading()==null) return "not set";
        return  exerciseReading.getReading().getName() + " id " + exerciseReading.getReading().getId();
    }
}
