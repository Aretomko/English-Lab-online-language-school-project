package com.example.application.views.answers.reading.readingAnswers;

import com.example.application.domain.AnswerReading;
import com.example.application.domain.ExerciseReading;
import com.vaadin.flow.component.grid.Grid;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.stream.Collectors;

@Service
public class CreateReadingAnswerAdminAnswersGridService {
    private Grid<AnswerReading> grid;

    public Grid<AnswerReading> createAnswersReadingGridByReading(ExerciseReading exerciseReading){
        this.grid = new Grid<>();
        grid.setItems(exerciseReading.getAnswersReading().stream().sorted(Comparator.comparing(AnswerReading::getId)).collect(Collectors.toList()));
        grid.addColumn(item -> item.getUser().getRealN()).setHeader("Student name");
        grid.addColumn(item -> item.getUser().getSurname()).setHeader("Student surname");
        grid.addColumn(AnswerReading::getAnswer).setHeader("Users answer");
        grid.addColumn(item->item.getExerciseReading().getRightAnswer()).setHeader("Right answer");
        grid.addColumn(item -> item.getExerciseReading().getAnswers()).setHeader("Possible answers");
        return grid;
    }

    public Grid<AnswerReading> getGrid() {
        return grid;
    }
}
