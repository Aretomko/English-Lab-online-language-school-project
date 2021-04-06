package com.example.application.views.admin.usersAdmin;

import com.example.application.domain.User;
import com.example.application.service.CreateAdminGridService;
import com.example.application.service.TeamService;
import com.example.application.service.UserService;
import com.example.application.views.admin.NavbarAdmin;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.ListDataProvider;

public class UsersAdminView extends VerticalLayout {

    private final UserService userService;
    private final TeamService teamService;
    //UI components
    private NavbarAdmin navbarAdmin;
    private Grid<User> grid;
    private HorizontalLayout modificationComponentDisplayed;

    public UsersAdminView(UserService userService, TeamService teamService, CreateGridUserService createGridUserService){
        this.userService = userService;
        this.teamService = teamService;
        //UI initialization
        this.navbarAdmin = new NavbarAdmin();
        this.grid = createGridUserService.createGridUsers();
        CreateUserComponent createUserComponent = new CreateUserComponent(teamService, userService, grid);
        this.modificationComponentDisplayed = createUserComponent;
        grid.addItemClickListener(item -> editUserEvent(item.getItem(), createUserComponent));
        this.displayUiComponents();
    }

    private void editUserEvent(User item, CreateUserComponent createUserComponent) {
        EditUserComponent editUserComponent = new EditUserComponent(item, teamService, userService, grid,createUserComponent, navbarAdmin,this);
        this.modificationComponentDisplayed = editUserComponent;
        this.removeAll();
        this.displayUiComponents();
    }

    private void displayUiComponents(){
        this.add(navbarAdmin, modificationComponentDisplayed, grid);
    }


}
