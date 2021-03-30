package com.example.application.views.answers.reading.readingAnswers;

import com.example.application.domain.*;
import com.example.application.service.CreateAdminGridService;
import com.example.application.service.ReadingExercisesService;
import com.example.application.service.TeamService;
import com.example.application.views.admin.NavbarAdmin;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.VaadinSession;

import java.util.ArrayList;
import java.util.List;

public class ReadingAnswersViewAdminAnswers extends VerticalLayout {
    public ReadingAnswersViewAdminAnswers(ReadingExercisesService readingExercisesService,
                                          CreateReadingAnswerAdminAnswersGridService createReadingAnswerAdminAnswersGridService,
                                          TeamService teamService){
        //create navbar
        NavbarAdmin navbarAdmin = new NavbarAdmin();
        this.add(navbarAdmin);
        //find lesson
        String readingExerciseId = VaadinSession.getCurrent().getAttribute("exerciseId").toString();
        ExerciseReading exerciseReading = readingExercisesService.getExerciseReadingById(readingExerciseId);
        //create explanation label
        Label label = new Label("All answers for exercise: "+ exerciseReading.getId());
        this.add(label);
        String teamId = VaadinSession.getCurrent().getAttribute("teamId").toString();
        Team team = teamService.getTeamById(teamId);
        List<User> users = team.getUsers();
        //create grid with all grammarExercises related to the lesson
        Grid<User> grid = createReadingAnswerAdminAnswersGridService.createAnswersReadingGridByReading(users, exerciseReading);
        this.add(grid);
    }
}
