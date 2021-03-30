package com.example.application.views.answers.listening.listenitingAnswers;

import com.example.application.domain.*;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CreateListeningAnswersAdminAnswersGridService {
    private Grid<User> grid;

    public Grid<User> createAnswersListeningGridByListingExercise(List<User> users,
                                                                  ExerciseListening ex){
        this.grid = new Grid<User>();
        grid.setItems(users);
        grid.addColumn(User::getRealN).setHeader("Name");
        grid.addColumn(User::getSurname).setHeader("Surname");
        grid.addComponentColumn(item-> new Label(ex.getRightAnswer())).setHeader("Right answer");
        grid.addComponentColumn(item-> new Label(ex.getAnswers())).setHeader("Right answers");
        grid.addComponentColumn(item -> this.getListeningExerciseAnswer(item, ex)).setHeader("Answer");
        return grid;
    }

    private Component getListeningExerciseAnswer(User user,
                                                 ExerciseListening exerciseListening){
        List<Answer> answers = user.getAnswersReading()
                .stream()
                .filter(i->i.getExerciseReading().equals(exerciseListening))
                .collect(Collectors.toList());
        return receiveOutputAnswers(answers,exerciseListening);
    }

    private Component receiveOutputAnswers(List<Answer> answers,
                                           Exercise exercise){
        if (answers.size()>1) return new Label("Internal error")
                ;
        if (answers.size()==0) return new Label("Not answered yet");
        if (answers.get(0).getAnswer().equals(exercise.getRightAnswer())) {
            return new Label(answers.get(0).getAnswer() + " true");
        }else{
            return new Label(answers.get(0).getAnswer() + " false");
        }
    }

    public Grid<User> getGrid() {
        return grid;
    }
}
