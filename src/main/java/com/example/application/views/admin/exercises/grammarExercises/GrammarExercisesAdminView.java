package com.example.application.views.admin.exercises.grammarExercises;

import com.example.application.domain.ExerciseGrammar;
import com.example.application.domain.User;
import com.example.application.service.*;
import com.example.application.views.admin.NavbarAdmin;
import com.example.application.views.admin.usersAdmin.CreateUserComponent;
import com.example.application.views.admin.usersAdmin.EditUserComponent;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class GrammarExercisesAdminView extends VerticalLayout {
    //UI components
    private NavbarAdmin navbarAdmin;
    private Grid<ExerciseGrammar> grid;
    private HorizontalLayout modificationComponentDisplayed;

    public GrammarExercisesAdminView(CreateGrammarExerciseGridService createGrammarExerciseGridService,
                                     GrammarExerciseService grammarExerciseService,
                                     LessonsService lessonsService){
        this.navbarAdmin = new NavbarAdmin();
        this.grid = createGrammarExerciseGridService.crateGridExerciseGrammar();
        CreateGrammarExerciseComponent createGrammarExerciseComponent = new CreateGrammarExerciseComponent(grammarExerciseService, lessonsService, grid);
        this.modificationComponentDisplayed = createGrammarExerciseComponent;
        grid.addItemClickListener(item -> editGrammarExerciseEvent(item.getItem(),lessonsService, grammarExerciseService,grid,navbarAdmin,createGrammarExerciseComponent));
        this.addUIComponents();
    }

    private void editGrammarExerciseEvent(ExerciseGrammar exerciseGrammar,
                                          LessonsService lessonsService,
                                          GrammarExerciseService grammarExerciseService,
                                          Grid<ExerciseGrammar> grid,
                                          NavbarAdmin navbarAdmin,
                                          CreateGrammarExerciseComponent createGrammarExerciseComponent) {
        EditGrammarExerciseComponent editGrammarExerciseComponent = new EditGrammarExerciseComponent(exerciseGrammar, lessonsService,grammarExerciseService, grid, navbarAdmin, createGrammarExerciseComponent, this);
        this.modificationComponentDisplayed = editGrammarExerciseComponent;
        this.removeAll();
        this.addUIComponents();
    }
    public void addUIComponents(){
        this.add(navbarAdmin, modificationComponentDisplayed, grid);
    }

    public NavbarAdmin getNavbarAdmin() {
        return navbarAdmin;
    }

    public Grid<ExerciseGrammar> getGrid() {
        return grid;
    }

    public HorizontalLayout getModificationComponentDisplayed() {
        return modificationComponentDisplayed;
    }
}
