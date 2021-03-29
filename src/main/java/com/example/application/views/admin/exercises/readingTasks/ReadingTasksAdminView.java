package com.example.application.views.admin.exercises.readingTasks;

import com.example.application.domain.Reading;
import com.example.application.service.CreateAdminGridService;
import com.example.application.service.LessonsService;
import com.example.application.service.ReadingService;
import com.example.application.views.admin.NavbarAdmin;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class ReadingTasksAdminView extends VerticalLayout {
    private final ReadingService readingService;
    private final LessonsService lessonsService;
    //UI components
    private NavbarAdmin navbarAdmin;
    private Grid<Reading> grid;
    private HorizontalLayout modificationComponentDisplayed;

    public ReadingTasksAdminView(CreateReadingGridService createReadingGridService, ReadingService readingService, LessonsService lessonsService){
        this.readingService = readingService;
        this.lessonsService = lessonsService;
        //UI initialization
        this.navbarAdmin = new NavbarAdmin();
        this.grid = createReadingGridService.createGridReading();
        CreateReadingTaskComponent createReadingTaskComponent = new CreateReadingTaskComponent(readingService, lessonsService, grid);
        this.modificationComponentDisplayed = createReadingTaskComponent;
        grid.addItemClickListener(item ->editTeamEvent(item.getItem(),createReadingTaskComponent));
        this.displayUIComponents();
    }

    private void editTeamEvent(Reading reading, CreateReadingTaskComponent createReadingTaskComponent){
        EditReadingTaskComponent editReadingTaskComponent = new EditReadingTaskComponent(reading, lessonsService, readingService, grid, this, navbarAdmin, createReadingTaskComponent);
        this.modificationComponentDisplayed = editReadingTaskComponent;
        this.removeAll();
        this.displayUIComponents();
    }

    private void displayUIComponents(){
        this.add(navbarAdmin, modificationComponentDisplayed, grid);
    }
}
