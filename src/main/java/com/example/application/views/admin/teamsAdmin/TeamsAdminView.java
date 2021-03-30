package com.example.application.views.admin.teamsAdmin;

import com.example.application.domain.Team;
import com.example.application.service.CourseService;
import com.example.application.service.TeamService;
import com.example.application.views.admin.NavbarAdmin;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class TeamsAdminView extends VerticalLayout {
    //dependencies
    private final CourseService courseService;
    private final TeamService teamService;
    //UI components
    private NavbarAdmin navbarAdmin;
    private Grid<Team> grid;
    private HorizontalLayout displayedModificationComponent;

    public TeamsAdminView(CreateGridTeamService createGridTeamService, CourseService courseService, TeamService teamService){
        //dependencies initialization
        this.courseService = courseService;
        this.teamService = teamService;
        //UI initialization
        this.navbarAdmin = new NavbarAdmin();
        this.grid = createGridTeamService.createGridTeams();
        CreateTeamComponent createTeamComponent = new CreateTeamComponent(courseService, teamService, grid);
        this.displayedModificationComponent= createTeamComponent;
        grid.addItemClickListener(item ->editTeamEvent(item.getItem(), createTeamComponent));
        this.showUi();
    }
    private void editTeamEvent(Team item, CreateTeamComponent createTeamComponent){
        //change create component on edit component
        this.displayedModificationComponent = new EditTeamComponent(item,
                courseService,
                teamService,
                grid,
                this,
                createTeamComponent,
                navbarAdmin);
        this.removeAll();
        this.showUi();
    }
    private void showUi(){
        this.add(navbarAdmin, displayedModificationComponent, grid);
    }
}
