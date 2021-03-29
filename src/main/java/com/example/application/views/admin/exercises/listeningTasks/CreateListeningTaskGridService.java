package com.example.application.views.admin.exercises.listeningTasks;

import com.example.application.domain.ExerciseGrammar;
import com.example.application.domain.Listening;
import com.example.application.service.LessonsService;
import com.example.application.service.ListeningService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.data.provider.ListDataProvider;
import org.springframework.stereotype.Service;

@Service
public class CreateListeningTaskGridService {
    private final ListeningService listeningService;
    private final LessonsService lessonsService;

    public CreateListeningTaskGridService(ListeningService listeningService, LessonsService lessonsService) {
        this.listeningService = listeningService;
        this.lessonsService = lessonsService;
    }

    public Grid<Listening> createGridListening(){
        Grid<Listening> grid = new Grid<>();
        grid.setItems(listeningService.getAllListings());
        grid.addColumn(Listening::getId).setHeader("Id");
        grid.addColumn(Listening::getName).setHeader("Name");
        grid.addColumn(Listening::getHomework).setHeader("Is Homework");
        grid.addColumn(item -> lessonsService.getStringNameIdByListening(item)).setHeader("Lesson");
        grid.addComponentColumn(item -> createRemoveButtonListening(grid, item)).setHeader("Delete listening task");
        return grid;
    }
    private Button createRemoveButtonListening(Grid<Listening> grid, Listening item) {
        @SuppressWarnings("unchecked")
        Button button = new Button("Delete", clickEvent -> {
            ListDataProvider<Listening> dataProvider = (ListDataProvider<Listening>) grid.getDataProvider();
            dataProvider.getItems().remove(item);
            listeningService.delete(item);
            dataProvider.refreshAll();
        });
        return button;
    }
}
