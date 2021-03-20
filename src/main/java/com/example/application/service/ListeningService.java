package com.example.application.service;

import com.example.application.domain.ExerciseListening;
import com.example.application.domain.Listening;
import com.example.application.repos.ListeningRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListeningService {
    private final ListeningRepo listeningRepo;

    public ListeningService(ListeningRepo listeningRepo) {
        this.listeningRepo = listeningRepo;
    }
    public void save(Listening listening){
        listeningRepo.save(listening);
    }
    public void delete(Listening listening){
        listeningRepo.delete(listening);
    }
    public List<Listening> getAllListings(){
        return listeningRepo.findAll();
    }
    public Listening findListeningById(String id){
        Long idLong = Long.valueOf(id.trim());
        Listening listening= listeningRepo.findById(idLong).orElse(null);
        if (listening == null) throw new RuntimeException("Wrong id provided");
        return listening;
    }
    public String getStringNameIdByListeningExercise(ExerciseListening exerciseListening){
        if (exerciseListening.getListening()==null) return "not set"
                ;
        return exerciseListening.getListening().getName() +" id "+ exerciseListening.getListening().getId();
    }
}
