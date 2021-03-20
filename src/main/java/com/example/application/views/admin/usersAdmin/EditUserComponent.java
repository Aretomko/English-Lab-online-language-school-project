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


public class EditUserComponent extends HorizontalLayout {
    public EditUserComponent(User user, TeamService teamService, UserService userService, Grid grid, CreateUserComponent createUserComponent, NavbarAdmin navbarAdmin, UsersAdminView adminView){
        //create label
        Label editLabel = new Label("Edit user " + user.getUsername());
        editLabel.setHeight("wrap-content");
        editLabel.getStyle().set("font","400 13.3333px Arial");
        editLabel.getStyle().set("font-size", "var(--lumo-font-size-s)");
        editLabel.getStyle().set("font-weight", "500");
        //create form field
        TextField userNameTextField = new TextField("name");
        TextField userSurnameTextField = new TextField("surname");
        EmailField emailField = new EmailField("email");
        if (user.getRealN()!=null) userNameTextField.setValue(user.getRealN())
                ;
        else userNameTextField.setPlaceholder("not set")
                ;
        if (user.getSurname()!=null) userSurnameTextField.setValue(user.getSurname())
                ;
        else userSurnameTextField.setPlaceholder("not set")
                ;
        if (user.getEmail() != null) emailField.setValue(user.getEmail())
                ;
        else emailField.setPlaceholder("not set")
                ;
        Select<String> placeholderSelect = new Select<>();
        placeholderSelect.setItems(teamService.getAllTeamsNames());
        if (user.getTeam() != null) placeholderSelect.setValue(user.getTeam().getName())
                ;
        else placeholderSelect.setPlaceholder("not selected")
                ;
        placeholderSelect.setLabel("team");
        //create button
            Button submit = new Button("Update" , event -> {
                user.setRealN(userNameTextField.getValue());
                user.setSurname(userSurnameTextField.getValue());
                user.setEmail(emailField.getValue());
                user.setTeam(teamService.getTeamByName(placeholderSelect.getValue()));
                userService.save(user);
                grid.getDataProvider().refreshItem(user);
                adminView.removeAll();
                adminView.add(navbarAdmin, createUserComponent, grid);
            });
        this.add(editLabel ,userNameTextField, userSurnameTextField, emailField, placeholderSelect, submit);
        this.setWidth("100%");
        this.setPadding(true);
        this.setAlignItems(Alignment.BASELINE);
    }
}
