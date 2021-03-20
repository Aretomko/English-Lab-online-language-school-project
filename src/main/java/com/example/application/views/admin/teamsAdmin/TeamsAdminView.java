package com.example.application.views.admin.teamsAdmin;

import com.example.application.domain.Team;
import com.example.application.service.CourseService;
import com.example.application.service.CreateAdminGridService;
import com.example.application.service.TeamService;
import com.example.application.views.admin.NavbarAdmin;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class TeamsAdminView extends VerticalLayout {
    public TeamsAdminView(CreateAdminGridService createAdminGridService, CourseService courseService, TeamService teamService){
        NavbarAdmin navbarAdmin = new NavbarAdmin();
        add(navbarAdmin);
        Grid<Team> grid = createAdminGridService.createGridTeams();
        CreateTeamComponent createTeamComponent = new CreateTeamComponent(courseService, teamService, grid);
        grid.addItemClickListener(item ->editTeamEvent(item.getItem(), navbarAdmin, grid, courseService, teamService, createTeamComponent));
        this.add(navbarAdmin, createTeamComponent, grid);
    }
    private void editTeamEvent(Team item, NavbarAdmin navbarAdmin, Grid grid, CourseService courseService, TeamService teamService, CreateTeamComponent createTeamComponent){
        EditTeamComponent editTeamComponent = new EditTeamComponent(item, courseService, teamService, grid, this, createTeamComponent, navbarAdmin);
        this.removeAll();
        this.add(navbarAdmin, editTeamComponent, grid);
    }
}
