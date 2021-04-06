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

import java.nio.channels.Selector;


public class EditUserComponent extends HorizontalLayout {
    private final User user;
    private final TeamService teamService;
    private final UserService userService;
    private final Grid<User> grid;
    private final CreateUserComponent createUserComponent;
    private final NavbarAdmin navbarAdmin;
    private final UsersAdminView adminView;

    private Label editLabel;
    private TextField userNameTextField;
    private TextField userSurnameTextField;
    private EmailField emailField;
    private Select<String> teamSelect;
    private Button submit;

    public EditUserComponent(User user,
                             TeamService teamService,
                             UserService userService,
                             Grid<User> grid,
                             CreateUserComponent createUserComponent,
                             NavbarAdmin navbarAdmin,
                             UsersAdminView adminView){
        this.user = user;
        this.teamService = teamService;
        this.userService = userService;
        this.grid = grid;
        this.createUserComponent = createUserComponent;
        this.navbarAdmin = navbarAdmin;
        this.adminView = adminView;
        //create label
        editLabel = new Label("Edit user " + user.getUsername());
        editLabel.setHeight("wrap-content");
        editLabel.getStyle().set("font","400 13.3333px Arial");
        editLabel.getStyle().set("font-size", "var(--lumo-font-size-s)");
        editLabel.getStyle().set("font-weight", "500");
        //create form field
        userNameTextField = new TextField("name");
        userSurnameTextField = new TextField("surname");
        emailField = new EmailField("email");
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
        teamSelect = new Select<>();
        teamSelect.setItems(teamService.getAllTeamsNames());
        if (user.getTeam() != null) teamSelect.setValue(user.getTeam().getName())
                ;
        else teamSelect.setPlaceholder("not selected")
                ;
        teamSelect.setLabel("team");
        //create button
        submit = new Button("Update" , event -> {
            this.updateUser();
        });
        this.add(editLabel ,userNameTextField, userSurnameTextField, emailField, teamSelect, submit);
        this.setWidth("100%");
        this.setPadding(true);
        this.setAlignItems(Alignment.BASELINE);
    }

    public void updateUser(){
        user.setRealN(userNameTextField.getValue());
        user.setSurname(userSurnameTextField.getValue());
        user.setEmail(emailField.getValue());
        user.setTeam(teamService.getTeamByName(teamSelect.getValue()));
        userService.save(user);
        grid.getDataProvider().refreshItem(user);
        adminView.removeAll();
        adminView.add(navbarAdmin, createUserComponent, grid);
    }
}
