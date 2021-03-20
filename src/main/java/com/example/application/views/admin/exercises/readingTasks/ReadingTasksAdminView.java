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
    public ReadingTasksAdminView(CreateAdminGridService createAdminGridService, ReadingService readingService, LessonsService lessonsService){
        NavbarAdmin navbarAdmin = new NavbarAdmin();
        add(navbarAdmin);
        Grid<Reading> grid = createAdminGridService.createGridReading();
        CreateReadingTaskComponent createReadingTaskComponent = new CreateReadingTaskComponent(readingService, lessonsService, grid);
        grid.addItemClickListener(item ->editTeamEvent(item.getItem(), navbarAdmin, grid, lessonsService,readingService,createReadingTaskComponent));
        this.add(navbarAdmin, createReadingTaskComponent, grid);
    }

    private void editTeamEvent(Reading reading,
                               NavbarAdmin navbarAdmin,
                               Grid<Reading> grid,
                               LessonsService lessonsService,
                               ReadingService readingService,
                               CreateReadingTaskComponent createReadingTaskComponent){
        EditReadingTaskComponent editReadingTaskComponent = new EditReadingTaskComponent(reading, lessonsService, readingService, grid, this, navbarAdmin, createReadingTaskComponent);
        this.removeAll();
        this.add(navbarAdmin, editReadingTaskComponent, grid);
    }
}
