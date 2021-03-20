package com.example.application.views.admin.exercises.readingExercises;

import com.example.application.domain.ExerciseReading;
import com.example.application.domain.User;
import com.example.application.service.*;
import com.example.application.views.admin.NavbarAdmin;
import com.example.application.views.admin.usersAdmin.CreateUserComponent;
import com.example.application.views.admin.usersAdmin.EditUserComponent;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class ReadingExercisesAdminView extends VerticalLayout {
    public ReadingExercisesAdminView(CreateAdminGridService createAdminGridService,
                                     ReadingService readingService,
                                     ReadingExercisesService readingExercisesService){
        NavbarAdmin navbarAdmin = new NavbarAdmin();
        add(navbarAdmin);
        Grid<ExerciseReading> grid = createAdminGridService.createGridExerciseReading();
        CreateReadingExerciseComponent createReadingExerciseComponent = new CreateReadingExerciseComponent(readingExercisesService,readingService,grid);
        grid.addItemClickListener(item -> editReadingExerciseEvent(item.getItem(),readingExercisesService, readingService,grid,navbarAdmin,createReadingExerciseComponent));
        this.add(navbarAdmin, createReadingExerciseComponent, grid);
    }
    private void editReadingExerciseEvent(ExerciseReading exerciseReading,
                                          ReadingExercisesService readingExercisesService,
                                          ReadingService readingService,
                                          Grid<ExerciseReading> grid,
                                          NavbarAdmin navbarAdmin,
                                          CreateReadingExerciseComponent createReadingExerciseComponent) {
        EditReadingExerciseComponent editReadingExerciseComponent = new EditReadingExerciseComponent(exerciseReading,
                readingExercisesService,
                readingService,
                grid,
                this,
                navbarAdmin,
                createReadingExerciseComponent);
        this.removeAll();
        this.add(navbarAdmin,editReadingExerciseComponent, grid);
    }

}
