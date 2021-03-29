package com.example.application.views.admin.exercises.listeningExercise;

import com.example.application.domain.ExerciseListening;
import com.example.application.service.ListeningExerciseService;
import com.example.application.service.ListeningService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.data.provider.ListDataProvider;
import org.springframework.stereotype.Service;

@Service
public class CreateListeningExerciseGridService {
    private final ListeningExerciseService listeningExerciseService;
    private final ListeningService listeningService;

    public CreateListeningExerciseGridService(ListeningExerciseService listeningExerciseService, ListeningService listeningService) {
        this.listeningExerciseService = listeningExerciseService;
        this.listeningService = listeningService;
    }

    public Grid<ExerciseListening> createGridExerciseListening(){
        Grid<ExerciseListening> grid = new Grid<>();
        grid.setItems(listeningExerciseService.getAllListeningExercises());
        grid.addColumn(ExerciseListening::getText).setHeader("Question");
        grid.addColumn(ExerciseListening::getAnswers).setHeader("Answers");
        grid.addColumn(item -> listeningService.getStringNameIdByListeningExercise(item)).setHeader("Listening task");
        grid.addComponentColumn(item-> createRemoveButtonExercisesListening(grid,item)).setHeader("Delete");
        return grid;
    }


    public Button createRemoveButtonExercisesListening(Grid<ExerciseListening> grid, ExerciseListening exerciseListening){
        Button button = new Button("Delete", event->{
            ListDataProvider<ExerciseListening> dataProvider =  (ListDataProvider<ExerciseListening>) grid.getDataProvider();
            dataProvider.getItems().remove(exerciseListening);
            listeningExerciseService.delete(exerciseListening);
            dataProvider.refreshAll();
        });
        return button;
    }

}
