package com.example.application.views.answers.reading.readingAnswers;

import com.example.application.domain.AnswerReading;
import com.example.application.domain.ExerciseReading;
import com.vaadin.flow.component.grid.Grid;
import org.springframework.stereotype.Service;

@Service
public class CreateReadingAnswerAdminAnswersGridService {
    private Grid<AnswerReading> grid;

    public Grid<AnswerReading> createAnswersReadingGridByReading(ExerciseReading exerciseReading){
        this.grid = new Grid<>();
        grid.setItems(exerciseReading.getAnswersReading());
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
