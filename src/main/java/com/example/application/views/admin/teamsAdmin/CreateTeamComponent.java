package com.example.application.views.admin.teamsAdmin;

import com.example.application.domain.Team;
import com.example.application.service.CourseService;
import com.example.application.service.TeamService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;

public class  CreateTeamComponent extends HorizontalLayout {
    //dependencies
    private final CourseService courseService;
    private final TeamService teamService;
    private final Grid<Team> grid;
    //UI elements
    private Label editLabel;
    private TextField teamNameTextField;
    private TextField teamScheduleTextField;
    private Select<String> courseSelect;

    public CreateTeamComponent(CourseService courseService, TeamService teamService, Grid grid){
        this.courseService = courseService;
        this.teamService = teamService;
        this.grid = grid;
        //initialize UI elements
        this.editLabel = new Label("Create new group");
        this.editLabel.setHeight("wrap-content");
        this.editLabel.getStyle().set("font","400 13.3333px Arial");
        this.editLabel.getStyle().set("font-size", "var(--lumo-font-size-s)");
        this.editLabel.getStyle().set("font-weight", "500");
        //create form fields
        this.teamNameTextField = new TextField("team name");
        this.teamScheduleTextField = new TextField("team schedule");
        this.courseSelect = new Select<>();
        courseSelect.setItems(courseService.getAllCoursesNames());
        courseSelect.setPlaceholder("not selected");
        courseSelect.setLabel("course");
        //create button
        Button submit = new Button("Create" , event -> {
            this.createTeam();
        });
        this.add(editLabel ,teamNameTextField,teamScheduleTextField, courseSelect, submit);
        this.setWidth("100%");
        this.setPadding(true);
        this.setAlignItems(Alignment.BASELINE);
    }

    public void createTeam(){
        teamService.save(new Team(teamNameTextField.getValue(),teamScheduleTextField.getValue(), courseService.getCourseByName(courseSelect.getValue())));
        grid.setItems(teamService.getAllTeams());
        teamNameTextField.setValue("");
        teamScheduleTextField.setValue("");
        courseSelect.setValue("");
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
