package com.example.application.views.answers.reading.readingExercises;

import com.example.application.domain.ExerciseReading;
import com.example.application.domain.Lesson;
import com.example.application.domain.Reading;
import com.example.application.service.CreateAdminGridService;
import com.example.application.service.LessonsService;
import com.example.application.service.ReadingService;
import com.example.application.views.admin.NavbarAdmin;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.VaadinSession;

public class ReadingExercisesViewAdminAnswers extends VerticalLayout {


    public ReadingExercisesViewAdminAnswers(ReadingService readingService,
                                            CreateReadingExercisesAdminAnswersGridService createReadingExercisesAdminAnswersGridService){
        //create navbar
        NavbarAdmin navbarAdmin = new NavbarAdmin();
        this.add(navbarAdmin);
        //find lesson
        String readingId = VaadinSession.getCurrent().getAttribute("readingId").toString();
        Reading reading = readingService.findReadingById(readingId);
        //create explanation label
        Label label = new Label("All reading exercises for reading task: "+ reading.getName());
        this.add(label);
        Grid<ExerciseReading> grid = createReadingExercisesAdminAnswersGridService.createReadingExercisesGridByReading(reading);
        this.add(grid);
    }






























































































































































}
