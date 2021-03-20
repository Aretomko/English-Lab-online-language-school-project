package com.example.application.views.admin.exercises.listeningTasks;

import com.example.application.domain.Listening;
import com.example.application.domain.User;
import com.example.application.service.*;
import com.example.application.views.admin.NavbarAdmin;
import com.example.application.views.admin.exercises.readingTasks.EditReadingTaskComponent;
import com.example.application.views.admin.usersAdmin.CreateUserComponent;
import com.example.application.views.admin.usersAdmin.EditUserComponent;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class ListeningTasksAdminView extends VerticalLayout {
    public ListeningTasksAdminView(CreateAdminGridService createAdminGridService, ListeningService listeningService, LessonsService lessonsService){
        NavbarAdmin navbarAdmin = new NavbarAdmin();
        add(navbarAdmin);
        Grid<Listening> grid = createAdminGridService.createGridListening();
        CreateListeningTaskComponent createListeningTaskComponent = new CreateListeningTaskComponent(listeningService, lessonsService, grid);
        grid.addItemClickListener(item -> editUserEvent(item.getItem(), lessonsService, listeningService, grid,createListeningTaskComponent, navbarAdmin));
        this.add(navbarAdmin, createListeningTaskComponent, grid);
    }
    //Listening listening, LessonsService lessonsService, ListeningService listeningService, Grid<Listening> grid, CreateListeningTaskComponent createListeningTaskComponent, NavbarAdmin navbarAdmin
    private void editUserEvent(Listening item,
                               LessonsService lessonsService,
                               ListeningService listeningService,
                               Grid<Listening> grid,
                               CreateListeningTaskComponent createListeningTaskComponent,
                               NavbarAdmin navbarAdmin) {
        EditListeningTaskComponent editUserComponent = new EditListeningTaskComponent(item,
                lessonsService,
                listeningService,
                grid,
                createListeningTaskComponent,
                navbarAdmin,
                this);
        this.removeAll();
        this.add(navbarAdmin,editUserComponent, grid);
    }
}
