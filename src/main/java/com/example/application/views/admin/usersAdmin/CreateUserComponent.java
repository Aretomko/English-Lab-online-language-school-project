package com.example.application.views.admin.usersAdmin;

import com.example.application.domain.Role;
import com.example.application.domain.User;
import com.example.application.service.TeamService;
import com.example.application.service.UserService;
import com.example.application.views.admin.NavbarAdmin;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;

import java.util.Collections;
import java.util.List;

public class CreateUserComponent extends HorizontalLayout {
    //Dependencies
    private final TeamService teamService;
    private final UserService userService;
    private final Grid<User> grid;

    //UI elements
    private Label editLabel;
    private TextField userNameTextField;
    private TextField userSurnameTextField;
    private EmailField emailField;
    private Select<String> teamSelect;

    public CreateUserComponent(TeamService teamService, UserService userService, Grid<User> grid){
        //final initialition
        this.teamService = teamService;
        this.userService = userService;
        this.grid = grid;
        //UI initialization
        //create label
        this.editLabel = new Label("Create new user");
        editLabel.setHeight("wrap-content");
        editLabel.getStyle().set("font","400 13.3333px Arial");
        editLabel.getStyle().set("font-size", "var(--lumo-font-size-s)");
        editLabel.getStyle().set("font-weight", "500");
        //create form field
        this.userNameTextField = new TextField("name");
        this.userSurnameTextField = new TextField("surname");
        this.emailField = new EmailField("email");
        this.teamSelect = new Select<>();
        this.teamSelect.setItems(teamService.getAllTeamsNames());
        this.teamSelect.setPlaceholder("not selected");
        this.teamSelect.setLabel("team");
        //create button
        Button submit = new Button("Create" , event -> {
            this.createUser();
        });
        this.add(editLabel ,userNameTextField, userSurnameTextField, emailField, teamSelect, submit);
        this.setWidth("100%");
        this.setPadding(true);
        this.setAlignItems(Alignment.BASELINE);
    }

    public void createUser(){
        User user = new User(userNameTextField.getValue(),
                userSurnameTextField.getValue(),
                emailField.getValue(),
                teamService.getTeamByName(teamSelect.getValue()));
        user.setRoles(Collections.singleton(Role.USER));
        userService.save(user);
        grid.setItems(userService.getAllUsers());
        userNameTextField.setValue("");
        userSurnameTextField.setValue("");
        emailField.setValue("");
        teamSelect.setValue("");
    }

    public Label getEditLabel() {
        return editLabel;
    }

    public TextField getUserNameTextField() {
        return userNameTextField;
    }

    public TextField getUserSurnameTextField() {
        return userSurnameTextField;
    }

    public EmailField getEmailField() {
        return emailField;
    }

    public Select<String> getTeamSelect() {
        return teamSelect;
    }
}
