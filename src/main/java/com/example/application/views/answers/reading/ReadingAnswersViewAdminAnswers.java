package com.example.application.views.answers.reading;

import com.example.application.domain.AnswerReading;
import com.example.application.domain.ExerciseGrammar;
import com.example.application.domain.ExerciseReading;
import com.example.application.domain.Lesson;
import com.example.application.service.CreateAdminGridService;
import com.example.application.service.ReadingExercisesService;
import com.example.application.views.admin.NavbarAdmin;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.VaadinSession;

public class ReadingAnswersViewAdminAnswers extends VerticalLayout {
    public ReadingAnswersViewAdminAnswers(ReadingExercisesService readingExercisesService,
                                          CreateAdminGridService createAdminGridService){
        //create navbar
        NavbarAdmin navbarAdmin = new NavbarAdmin();
        this.add(navbarAdmin);
        //find lesson
        String readingExerciseId = VaadinSession.getCurrent().getAttribute("exerciseId").toString();
        ExerciseReading exerciseReading = readingExercisesService.getExerciseReadingById(readingExerciseId);
        //create explanation label
        Label label = new Label("All answers for exercise: "+ exerciseReading.getId());
        this.add(label);
        //create grid with all grammarExercises related to the lesson
        Grid<AnswerReading> grid = createAdminGridService.createAnswersReadingGridByReading(exerciseReading);
        this.add(grid);
    }
}
