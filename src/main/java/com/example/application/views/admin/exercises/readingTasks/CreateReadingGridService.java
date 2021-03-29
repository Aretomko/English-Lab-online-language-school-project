package com.example.application.views.admin.exercises.readingTasks;

import com.example.application.domain.ExerciseGrammar;
import com.example.application.domain.Reading;
import com.example.application.service.LessonsService;
import com.example.application.service.ReadingService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.stereotype.Service;

@Service
public class CreateReadingGridService {
    private final ReadingService readingService;
    private final LessonsService lessonsService;

    private Grid<Reading> grid;

    public CreateReadingGridService(ReadingService readingService, LessonsService lessonsService) {
        this.readingService = readingService;
        this.lessonsService = lessonsService;
    }

    public Grid<Reading> createGridReading(){
        this.grid = new Grid<>();
        grid.setItems(readingService.getAllReadings());
        grid.addColumn(Reading::getId).setHeader("Id");
        grid.addColumn(Reading::getName).setHeader("Name");
        grid.addComponentColumn(this::createEditTextButton);
        grid.addColumn(Reading::getHomework).setHeader("Is Homework");
        grid.addColumn(lessonsService::getStringNameIdByReading).setHeader("Lesson");
        grid.addComponentColumn(item ->createRemoveButtonReading(grid, item, readingService)).setHeader("Delete reading");
        return grid;
    }
    private Button createEditTextButton(Reading reading){
        return new Button("Edit text", event->{
            VaadinSession.getCurrent().setAttribute("readingId", reading.getId());
            UI.getCurrent().navigate("admin/editTextReading");
        });
    }
    private Component createRemoveButtonReading(Grid<Reading> grid, Reading reading, ReadingService readingService){
        @SuppressWarnings("unchecked")
        Button button = new Button("Delete" , clickEvent ->{
            ListDataProvider<Reading> dataProvider = (ListDataProvider<Reading>) grid.getDataProvider();
            dataProvider.getItems().remove(reading);
            readingService.delete(reading);
            dataProvider.refreshAll();
        });
        return button;
    }

    public Grid<Reading> getGrid() {
        return grid;
    }
}
