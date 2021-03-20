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
    public EditTeamComponent(Team team, CourseService courseService, TeamService teamService, Grid grid, TeamsAdminView teamsAdminView, CreateTeamComponent createTeamComponent, NavbarAdmin navbarAdmin){
        Label editLabel = new Label("Edit group " + team.getName());
        editLabel.setHeight("wrap-content");
        editLabel.getStyle().set("font","400 13.3333px Arial");
        editLabel.getStyle().set("font-size", "var(--lumo-font-size-s)");
        editLabel.getStyle().set("font-weight", "500");
        //create form field
        TextField teamNameTextField = new TextField("name");
        TextField teamScheduleTextField = new TextField("surname");
        if (team.getName()!= null) teamNameTextField.setValue(team.getName())
                ;
        else teamNameTextField.setPlaceholder("not set")
                ;
        if (team.getSchedule()!=null) teamScheduleTextField.setValue(team.getSchedule())
                ;
        else teamScheduleTextField.setPlaceholder("not set")
                ;
        Select<String> placeholderSelect = new Select<>();
        placeholderSelect.setItems(courseService.getAllCoursesNames());
        if (team.getCourse() != null) placeholderSelect.setValue(team.getCourse().getName())
                ;
        else placeholderSelect.setPlaceholder("not selected")
                ;
        placeholderSelect.setLabel("team");
        //create button
        Button submit = new Button("Update" , event -> {
            team.setName(teamNameTextField.getValue());
            team.setSchedule(teamScheduleTextField.getValue());
            team.setCourse(courseService.getCourseByName(placeholderSelect.getValue()));
            teamService.save(team);
            grid.getDataProvider().refreshItem(team);
            teamsAdminView.removeAll();
            teamsAdminView.add(navbarAdmin, createTeamComponent, grid);
        });
        this.add(editLabel ,teamNameTextField, teamScheduleTextField, placeholderSelect, submit);
        this.setWidth("100%");
        this.setPadding(true);
        this.setAlignItems(Alignment.BASELINE);
    }
}
