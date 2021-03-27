package com.example.application.views.admin.usersAdmin;

import com.example.application.domain.User;
import com.example.application.service.CreateAdminGridService;
import com.example.application.service.TeamService;
import com.example.application.service.UserService;
import com.example.application.views.admin.NavbarAdmin;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.provider.ListDataProvider;

public class UsersAdminView extends VerticalLayout {
    public UsersAdminView(UserService userService, TeamService teamService, CreateGridUserService createGridUserService){
        NavbarAdmin navbarAdmin = new NavbarAdmin();
        add(navbarAdmin);
        Grid<User> grid = createGridUserService.createGridUsers();
        CreateUserComponent createUserComponent = new CreateUserComponent(teamService, userService, grid);
        grid.addItemClickListener(item -> editUserEvent(item.getItem(), teamService, grid, navbarAdmin, userService, createUserComponent));
        this.add(navbarAdmin, createUserComponent, grid);
    }

    private void editUserEvent(User item, TeamService teamService, Grid<User> grid, NavbarAdmin navbarAdmin, UserService userService, CreateUserComponent createUserComponent) {
        EditUserComponent editUserComponent = new EditUserComponent(item, teamService, userService, grid,createUserComponent, navbarAdmin,this);
        this.removeAll();
        this.add(navbarAdmin,editUserComponent, grid);
    }


}
