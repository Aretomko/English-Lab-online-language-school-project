package com.example.application.views.admin.exercises.listeningExercise;

import com.example.application.domain.ExerciseListening;
import com.example.application.domain.User;
import com.example.application.service.*;
import com.example.application.views.admin.NavbarAdmin;
import com.example.application.views.admin.usersAdmin.CreateUserComponent;
import com.example.application.views.admin.usersAdmin.EditUserComponent;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class ListeningExercisesAdminView extends VerticalLayout {
    public ListeningExercisesAdminView(CreateAdminGridService createAdminGridService,
                                       ListeningExerciseService listeningExerciseService,
                                       ListeningService listeningService){
        NavbarAdmin navbarAdmin = new NavbarAdmin();
        add(navbarAdmin);
        Grid<ExerciseListening> grid = createAdminGridService.createGridExerciseListening();
        CreateListeningExerciseComponent createListeningExerciseComponent = new CreateListeningExerciseComponent(listeningExerciseService, listeningService, grid);
        grid.addItemClickListener(item -> editListeningExerciseEvent(item.getItem(), listeningService, listeningExerciseService,grid,navbarAdmin,createListeningExerciseComponent));
        this.add(navbarAdmin, createListeningExerciseComponent, grid);
    }

    private void editListeningExerciseEvent(ExerciseListening item,
                                            ListeningService listeningService,
                                            ListeningExerciseService listeningExerciseService,
                                            Grid<ExerciseListening> grid,
                                            NavbarAdmin navbarAdmin,
                                            CreateListeningExerciseComponent createListeningExerciseComponent) {
        EditListeningExerciseComponent editListeningExerciseComponent = new EditListeningExerciseComponent(item, listeningService, listeningExerciseService,grid,this,navbarAdmin,createListeningExerciseComponent);
        this.removeAll();
        this.add(navbarAdmin,editListeningExerciseComponent, grid);
    }
}
