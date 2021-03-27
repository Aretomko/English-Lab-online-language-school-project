package com.example.application.views.admin.teamsAdmin;

import com.example.application.domain.Team;
import com.example.application.service.CourseService;
import com.example.application.service.TeamService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.stereotype.Service;

@Service
public class CreateGridTeamService {
    private final TeamService teamService;
    private final CourseService courseService;

    //grid
    private Grid<Team> grid;

    public CreateGridTeamService(TeamService teamService, CourseService courseService) {
        this.teamService = teamService;
        this.courseService = courseService;
    }

    public Grid<Team> createGridTeams(){
        this.grid = new Grid<>();
        grid.setItems(teamService.getAllTeams());
        grid.addColumn(Team::getName).setHeader("Team name");
        grid.addColumn(Team::getSchedule).setHeader("Team schedule");
        grid.addColumn(item -> courseService.getStringName(item)).setHeader("Course");
        grid.addComponentColumn(this::createAnswerInfoButton).setHeader("See answers");
        grid.addComponentColumn(this::createRemoveButtonTeams).setHeader("Delete team");
        return grid;
    }

    private Button createAnswerInfoButton(Team team){
        Button button = new Button("Answers", event->{
            VaadinSession.getCurrent().setAttribute("teamId", team.getId());
            UI.getCurrent().navigate("admin/answers");
        });
        return button;
    }

    private Component createRemoveButtonTeams(Team item) {
        @SuppressWarnings("unchecked")
        Button button = new Button("Delete", clickEvent -> {
            this.deleteTeam(item);
        });
        return button;
    }

    public void deleteTeam(Team item){
        ListDataProvider<Team> dataProvider = (ListDataProvider<Team>) grid.getDataProvider();
        dataProvider.getItems().remove(item);
        this.teamService.deleteTeamByName(item.getName());
        dataProvider.refreshAll();
    }

    public Grid<Team> getGrid() {
        return grid;
    }
}
