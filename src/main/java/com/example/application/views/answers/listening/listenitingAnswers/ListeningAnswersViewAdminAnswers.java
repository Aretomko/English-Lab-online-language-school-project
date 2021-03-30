package com.example.application.views.answers.listening.listenitingAnswers;

import com.example.application.domain.ExerciseListening;
import com.example.application.domain.ExerciseReading;
import com.example.application.domain.Team;
import com.example.application.domain.User;
import com.example.application.service.ListeningExerciseService;
import com.example.application.service.ReadingExercisesService;
import com.example.application.service.TeamService;
import com.example.application.views.admin.NavbarAdmin;
import com.example.application.views.answers.listening.listenitngExercises.CreateListeningExercisesAdminAnswersGridService;
import com.example.application.views.answers.vocabluary.vocabularyAnswers.CreateVocabularyAnswersAdminAnswersGridService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.VaadinSession;

import java.util.List;

public class ListeningAnswersViewAdminAnswers extends VerticalLayout {
    public ListeningAnswersViewAdminAnswers(ListeningExerciseService listeningExerciseService,
                                            TeamService teamService,
                                            CreateListeningAnswersAdminAnswersGridService createListeningAnswersAdminAnswersGridService){
        //create navbar
        NavbarAdmin navbarAdmin = new NavbarAdmin();
        this.add(navbarAdmin);
        //find lesson
        String listeningExerciseId = VaadinSession.getCurrent().getAttribute("exerciseId").toString();
        ExerciseListening exerciseListening = listeningExerciseService.getListeningExerciseById(listeningExerciseId);
        //create explanation label
        Label label = new Label("All answers for exercise: "+ exerciseListening.getId());
        this.add(label);
        String teamId = VaadinSession.getCurrent().getAttribute("teamId").toString();
        Team team = teamService.getTeamById(teamId);
        List<User> users = team.getUsers();
        //create grid with all grammarExercises related to the lesson
        Grid<User> grid = createListeningAnswersAdminAnswersGridService.createAnswersListeningGridByListingExercise(users, exerciseListening);
        this.add(grid);
    }
}
