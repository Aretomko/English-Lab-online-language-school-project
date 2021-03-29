package com.example.application.views.answers;

import com.example.application.domain.Lesson;
import com.example.application.domain.Team;
import com.example.application.service.CreateAdminGridService;
import com.example.application.service.TeamService;
import com.example.application.views.admin.NavbarAdmin;
import com.example.application.views.navbar.NavbarView;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.VaadinSession;


public class LessonsViewAnswersAdmin extends VerticalLayout {
    public LessonsViewAnswersAdmin(TeamService teamService,
                                   CreateAdminGridService createAdminGridService){
        String teamId = VaadinSession.getCurrent().getAttribute("teamId").toString();
        Team team = teamService.getTeamById(teamId);
        this.setWidth("100%");
        //create navbar
        NavbarAdmin navbarAdmin = new NavbarAdmin();
        this.add(navbarAdmin);
        //create explanation label
        Label explanationLabel = new Label("Now you see list of lessons for team - "+team.getName());
        Grid<Lesson> grid = createAdminGridService.createLessonsGridAnswersAdmin(team);
        this.add(explanationLabel,grid);
    }
}
