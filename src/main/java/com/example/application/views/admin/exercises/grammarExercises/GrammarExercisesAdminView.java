package com.example.application.views.admin.exercises.grammarExercises;

import com.example.application.domain.ExerciseGrammar;
import com.example.application.domain.User;
import com.example.application.service.*;
import com.example.application.views.admin.NavbarAdmin;
import com.example.application.views.admin.usersAdmin.CreateUserComponent;
import com.example.application.views.admin.usersAdmin.EditUserComponent;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class GrammarExercisesAdminView extends VerticalLayout {
    public GrammarExercisesAdminView(CreateAdminGridService createAdminGridService,
                                     GrammarExerciseService grammarExerciseService,
                                     LessonsService lessonsService){
        NavbarAdmin navbarAdmin = new NavbarAdmin();
        add(navbarAdmin);
        Grid<ExerciseGrammar> grid = createAdminGridService.crateGridExerciseGrammar();
        CreateGrammarExerciseComponent createGrammarExerciseComponent = new CreateGrammarExerciseComponent(grammarExerciseService, lessonsService, grid);
        grid.addItemClickListener(item -> editGrammarExerciseEvent(item.getItem(),lessonsService, grammarExerciseService,grid,navbarAdmin,createGrammarExerciseComponent));
        this.add(navbarAdmin, createGrammarExerciseComponent, grid);
    }

    private void editGrammarExerciseEvent(ExerciseGrammar exerciseGrammar,
                                          LessonsService lessonsService,
                                          GrammarExerciseService grammarExerciseService,
                                          Grid<ExerciseGrammar> grid,
                                          NavbarAdmin navbarAdmin,
                                          CreateGrammarExerciseComponent createGrammarExerciseComponent) {
        EditGrammarExerciseComponent editGrammarExerciseComponent = new EditGrammarExerciseComponent(exerciseGrammar, lessonsService,grammarExerciseService, grid, navbarAdmin, createGrammarExerciseComponent, this);
        this.removeAll();
        this.add(navbarAdmin,editGrammarExerciseComponent, grid);
    }
}
