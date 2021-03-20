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

public class CreateTeamComponent extends HorizontalLayout {
    public CreateTeamComponent(CourseService courseService, TeamService teamService, Grid grid){
        Label editLabel = new Label("Create new group");
        editLabel.setHeight("wrap-content");
        editLabel.getStyle().set("font","400 13.3333px Arial");
        editLabel.getStyle().set("font-size", "var(--lumo-font-size-s)");
        editLabel.getStyle().set("font-weight", "500");
        //create form fields
        TextField teamNameTextField = new TextField("team name");
        TextField teamScheduleTextField = new TextField("team schedule");
        Select<String> placeholderSelect = new Select<>();
        placeholderSelect.setItems(courseService.getAllCoursesNames());
        placeholderSelect.setPlaceholder("not selected");
        placeholderSelect.setLabel("course");
        //create button
        Button submit = new Button("Create" , event -> {
            teamService.save(new Team(teamNameTextField.getValue(),teamScheduleTextField.getValue(), courseService.getCourseByName(placeholderSelect.getValue())));
            grid.setItems(teamService.getAllTeams());
            teamNameTextField.setValue("");
            teamScheduleTextField.setValue("");
            placeholderSelect.setValue("");
        });
        this.add(editLabel ,teamNameTextField,teamScheduleTextField, placeholderSelect, submit);
        this.setWidth("100%");
        this.setPadding(true);
        this.setAlignItems(Alignment.BASELINE);
    }
}
