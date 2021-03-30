package com.example.application.views.answers.listening.listenitngExercises;

import com.example.application.domain.Exercise;
import com.example.application.domain.ExerciseListening;
import com.example.application.domain.ExerciseReading;
import com.example.application.domain.Listening;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.stereotype.Service;


import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CreateListeningExercisesAdminAnswersGridService {
    private Grid<ExerciseListening> grid;

    public Grid<ExerciseListening> createListeningExercisesGridByListening(Listening listening){
        this.grid = new Grid<ExerciseListening>();
        grid.setItems(listening.getExerciseListening().stream().sorted(Comparator.comparing(ExerciseListening::getId)).collect(Collectors.toList()));
        grid.addColumn(ExerciseListening::getAnswers).setHeader("Possible answers");
        grid.addColumn(ExerciseListening::getRightAnswer).setHeader("Right answer");
        grid.addComponentColumn(this::createSeeAnswersButtonExerciseListening).setHeader("See answers");
        return grid;
    }

    private Button createSeeAnswersButtonExerciseListening(ExerciseListening exerciseListening){
        Button button = new Button("See answers", event-> {
            VaadinSession.getCurrent().setAttribute("exerciseId", exerciseListening.getId());
            UI.getCurrent().navigate("admin/answers/answers/listening");
        });
        return button;
    }

    public Grid<ExerciseListening> getGrid() {
        return grid;
    }
}
