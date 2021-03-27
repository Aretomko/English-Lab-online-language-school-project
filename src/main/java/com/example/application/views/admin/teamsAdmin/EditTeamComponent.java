package com.example.application.views.admin.teamsAdmin;

import com.example.application.domain.Team;
import com.example.application.service.CourseService;
import com.example.application.service.TeamService;
import com.example.application.views.admin.NavbarAdmin;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;

public class EditTeamComponent extends HorizontalLayout {
    //dependencies
    private final CourseService courseService;
    private final TeamService teamService;
    private final Grid<Team> grid;
    private final TeamsAdminView teamsAdminView;
    private final CreateTeamComponent createTeamComponent;
    private final NavbarAdmin navbarAdmin;
    //UI componets
    private Label editLabel;
    private TextField teamNameTextField;
    private TextField teamScheduleTextField;
    private Select<String> courseSelect;

    public EditTeamComponent(Team team,
                             CourseService courseService,
                             TeamService teamService,
                             Grid grid,
                             TeamsAdminView teamsAdminView,
                             CreateTeamComponent createTeamComponent,
                             NavbarAdmin navbarAdmin){
        //dependencies initialization
        this.courseService = courseService;
        this.teamService = teamService;
        this.grid = grid;
        this.teamsAdminView = teamsAdminView;
        this.createTeamComponent = createTeamComponent;
        this.navbarAdmin = navbarAdmin;
        //UI initialization
        this.editLabel = new Label("Edit group " + team.getName());
        editLabel.setHeight("wrap-content");
        editLabel.getStyle().set("font","400 13.3333px Arial");
        editLabel.getStyle().set("font-size", "var(--lumo-font-size-s)");
        editLabel.getStyle().set("font-weight", "500");
        //create form field
        this.teamNameTextField = new TextField("name");
        this.teamScheduleTextField = new TextField("surname");
        if (team.getName()!= null) teamNameTextField.setValue(team.getName())
                ;
        else teamNameTextField.setPlaceholder("not set")
                ;
        if (team.getSchedule()!=null) teamScheduleTextField.setValue(team.getSchedule())
                ;
        else teamScheduleTextField.setPlaceholder("not set")
                ;
        this.courseSelect = new Select<>();
        courseSelect.setItems(courseService.getAllCoursesNames());
        courseSelect.setLabel("team");
        if (team.getCourse() != null) courseSelect.setValue(team.getCourse().getName())
                ;
        else courseSelect.setPlaceholder("not selected")
                ;
        //create button
        Button submit = new Button("Update" , event -> {
            this.updateTeam(team);
        });
        this.add(editLabel ,teamNameTextField, teamScheduleTextField, courseSelect, submit);
        this.setWidth("100%");
        this.setPadding(true);
        this.setAlignItems(Alignment.BASELINE);
    }

    public void updateTeam(Team team){
        team.setName(teamNameTextField.getValue());
        team.setSchedule(teamScheduleTextField.getValue());
        team.setCourse(courseService.getCourseByName(courseSelect.getValue()));
        teamService.save(team);
        grid.getDataProvider().refreshItem(team);
        teamsAdminView.removeAll();
        teamsAdminView.add(navbarAdmin, createTeamComponent, grid);
    }

    public Label getEditLabel() {
        return editLabel;
    }

    public TextField getTeamNameTextField() {
        return teamNameTextField;
    }

    public TextField getTeamScheduleTextField() {
        return teamScheduleTextField;
    }

    public Select<String> getCourseSelect() {
        return courseSelect;
    }
}
