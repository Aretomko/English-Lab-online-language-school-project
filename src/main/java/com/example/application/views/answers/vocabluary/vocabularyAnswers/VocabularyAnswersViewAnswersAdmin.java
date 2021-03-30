package com.example.application.views.answers.vocabluary.vocabularyAnswers;

import com.example.application.domain.ExerciseGrammar;
import com.example.application.domain.ExerciseVocabulary;
import com.example.application.domain.Team;
import com.example.application.domain.User;
import com.example.application.service.CreateAdminGridService;
import com.example.application.service.TeamService;
import com.example.application.service.VocabularyExerciseService;
import com.example.application.views.admin.NavbarAdmin;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.VaadinSession;

import java.util.List;

public class VocabularyAnswersViewAnswersAdmin extends VerticalLayout {
    public VocabularyAnswersViewAnswersAdmin(TeamService teamService,
                                             VocabularyExerciseService vocabularyExerciseService,
                                             CreateVocabularyAnswersAdminAnswersGridService createVocabularyAnswersAdminAnswersGridService){
        NavbarAdmin navbarAdmin = new NavbarAdmin();
        this.add(navbarAdmin);
        String teamId = VaadinSession.getCurrent().getAttribute("teamId").toString();
        Team team = teamService.getTeamById(teamId);
        //get grammar
        String vocabularyExerciseId = VaadinSession.getCurrent().getAttribute("vocabularyExerciseId").toString();
        ExerciseVocabulary exerciseVocabulary = vocabularyExerciseService.getById(vocabularyExerciseId);
        List<User> users = team.getUsers();
        Grid<User> grid = createVocabularyAnswersAdminAnswersGridService.createUserGridWithVocabularyAnswers(users,exerciseVocabulary);
        //create explanation
        this.add(new Label("Answers for exercise "+exerciseVocabulary.getId()+ " team " + team.getName()));
        this.add(grid);
    }
}
