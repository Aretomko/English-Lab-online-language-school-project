package com.example.application.views.answers.grammar;

import com.example.application.domain.ExerciseGrammar;
import com.example.application.domain.Team;
import com.example.application.domain.User;
import com.example.application.service.CreateAdminGridService;
import com.example.application.service.GrammarExerciseService;
import com.example.application.service.TeamService;
import com.example.application.views.admin.NavbarAdmin;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.VaadinSession;

import java.util.List;

public class GrammarAnswersViewAnswersAdmin extends VerticalLayout {
    public GrammarAnswersViewAnswersAdmin(TeamService teamService,
                                          CreateAdminGridService createAdminGridService,
                                          GrammarExerciseService grammarExerciseService){
        //create navbar
        NavbarAdmin navbarAdmin = new NavbarAdmin();
        this.add(navbarAdmin);
        String teamId = VaadinSession.getCurrent().getAttribute("teamId").toString();
        Team team = teamService.getTeamById(teamId);
        //get grammar
        String grammarExerciseId = VaadinSession.getCurrent().getAttribute("grammarExerciseId").toString();
        ExerciseGrammar grammarExercise = grammarExerciseService.getById(grammarExerciseId);
        List<User> users = team.getUsers();
        Grid<User> grid = createAdminGridService.createUsersGridWithGrammarAnswers(users, grammarExercise);
        //create explanation
        this.add(new Label("Answers for exercise "+grammarExercise.getId()+ " team " + team.getName()));
        this.add(grid);
    }
}
