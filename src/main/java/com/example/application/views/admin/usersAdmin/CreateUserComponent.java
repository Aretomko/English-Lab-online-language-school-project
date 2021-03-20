package com.example.application.views.admin.usersAdmin;

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

public class CreateUserComponent extends HorizontalLayout {
    public CreateUserComponent(TeamService teamService, UserService userService, UsersAdminView adminView, NavbarAdmin navbarAdmin, Grid grid){
        //create label
        Label editLabel = new Label("Create new user");
        editLabel.setHeight("wrap-content");
        editLabel.getStyle().set("font","400 13.3333px Arial");
        editLabel.getStyle().set("font-size", "var(--lumo-font-size-s)");
        editLabel.getStyle().set("font-weight", "500");
        //create form field
        TextField userNameTextField = new TextField("name");
        TextField userSurnameTextField = new TextField("surname");
        EmailField emailField = new EmailField("email");
        Select<String> placeholderSelect = new Select<>();
        placeholderSelect.setItems(teamService.getAllTeamsNames());
        placeholderSelect.setPlaceholder("not selected");
        placeholderSelect.setLabel("team");
        //create button
        Button submit = new Button("Create" , event -> {
            userService.save(new User(userNameTextField.getValue(), userSurnameTextField.getValue(), emailField.getValue(), teamService.getTeamByName(placeholderSelect.getValue())));
            grid.setItems(userService.getAllUsers());
            userNameTextField.setValue("");
            userSurnameTextField.setValue("");
            emailField.setValue("");
            placeholderSelect.setValue("");
        });
        this.add(editLabel ,userNameTextField, userSurnameTextField, emailField, placeholderSelect, submit);
        this.setWidth("100%");
        this.setPadding(true);
        this.setAlignItems(Alignment.BASELINE);
    }


}
