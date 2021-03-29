package com.example.application.views.answers.reading.readingExercises;

import com.example.application.domain.ExerciseReading;
import com.example.application.domain.Reading;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.stereotype.Service;

@Service
public class CreateReadingExercisesAdminAnswersGridService {
    private Grid<ExerciseReading> grid;

    public Grid<ExerciseReading> createReadingExercisesGridByReading(Reading reading){
        this.grid = new Grid<>();
        grid.setItems(reading.getExercisesReading());
        grid.addColumn(ExerciseReading::getAnswers).setHeader("Possible answers");
        grid.addColumn(ExerciseReading::getRightAnswer).setHeader("Right answer");
        grid.addComponentColumn(item->createSeeAnswersButtonExerciseReading(item));
        return grid;
    }
    private Button createSeeAnswersButtonExerciseReading(ExerciseReading exerciseReading){
        Button button = new Button("See answers", event-> {
            VaadinSession.getCurrent().setAttribute("exerciseId", exerciseReading.getId());
            UI.getCurrent().navigate("admin/answers/answers/reading");
        });
        return button;
    }

    public Grid<ExerciseReading> getGrid() {
        return grid;
    }
}
