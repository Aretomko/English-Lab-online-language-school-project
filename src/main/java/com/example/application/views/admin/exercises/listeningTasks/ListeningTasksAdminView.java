package com.example.application.views.admin.exercises.listeningTasks;

import com.example.application.domain.Listening;
import com.example.application.domain.User;
import com.example.application.service.*;
import com.example.application.views.admin.NavbarAdmin;
import com.example.application.views.admin.exercises.readingTasks.EditReadingTaskComponent;
import com.example.application.views.admin.usersAdmin.CreateUserComponent;
import com.example.application.views.admin.usersAdmin.EditUserComponent;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class ListeningTasksAdminView extends VerticalLayout {
    private final ListeningService listeningService;
    private final LessonsService lessonsService;
    //UI components
    private NavbarAdmin navbarAdmin;
    private Grid<Listening> grid;
    private HorizontalLayout modificationComponentDisplayed;

    public ListeningTasksAdminView(CreateListeningTaskGridService createListeningTaskGridService, ListeningService listeningService, LessonsService lessonsService){
        this.listeningService = listeningService;
        this.lessonsService = lessonsService;
        this.navbarAdmin = new NavbarAdmin();
        this.grid = createListeningTaskGridService.createGridListening();
        CreateListeningTaskComponent createListeningTaskComponent = new CreateListeningTaskComponent(listeningService, lessonsService, grid);
        this.modificationComponentDisplayed = createListeningTaskComponent;
        grid.addItemClickListener(item -> editUserEvent(item.getItem(),createListeningTaskComponent));
        this.displayUIComponents();
    }
    private void editUserEvent(Listening item,
                               CreateListeningTaskComponent createListeningTaskComponent) {
        EditListeningTaskComponent editListeningTaskComponent = new EditListeningTaskComponent(item,
                lessonsService,
                listeningService,
                grid,
                createListeningTaskComponent,
                navbarAdmin,
                this);
        this.modificationComponentDisplayed = editListeningTaskComponent;
        this.removeAll();
        this.displayUIComponents();
    }

    public void displayUIComponents(){
        this.add(navbarAdmin, modificationComponentDisplayed, grid);
    }
}
