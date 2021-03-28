package com.example.application.views.admin.exercises.vocabularyExercises;

import com.example.application.domain.ExerciseVocabulary;
import com.example.application.domain.Lesson;
import com.example.application.service.CreateAdminGridService;
import com.example.application.service.LessonsService;
import com.example.application.service.VocabularyExerciseService;
import com.example.application.views.admin.NavbarAdmin;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class VocabularyExerciseAdminAdmin extends VerticalLayout {
    private final VocabularyExerciseService vocabularyExerciseService;
    private final LessonsService lessonsService;
    //UI components
    private NavbarAdmin navbarAdmin;
    private Grid<ExerciseVocabulary> grid;
    private HorizontalLayout modificationComponentDisplayed;

    public VocabularyExerciseAdminAdmin(CreateVocabularyExercisesGridService createVocabularyExercisesGridService,
                                        VocabularyExerciseService vocabularyExerciseService,
                                        LessonsService lessonsService){
        this.vocabularyExerciseService = vocabularyExerciseService;
        this.lessonsService = lessonsService;
        //UI initialization
        this.navbarAdmin = new NavbarAdmin();
        this.grid = createVocabularyExercisesGridService.createGridExerciseVocabulary();
        CreateVocabularyExerciseComponent createVocabularyExerciseComponent = new CreateVocabularyExerciseComponent(vocabularyExerciseService,lessonsService, grid);
        this.modificationComponentDisplayed = createVocabularyExerciseComponent;
        grid.addItemClickListener(item -> editVocabluaryExerciseEvent(item.getItem(), createVocabularyExerciseComponent));
        this.displayUIComponents();
    }

    private void editVocabluaryExerciseEvent(ExerciseVocabulary item,
                                             CreateVocabularyExerciseComponent createVocabularyExerciseComponent) {
        EditVocabularyExerciseComponent editVocabularyExerciseComponent = new EditVocabularyExerciseComponent(item,
                lessonsService,
                vocabularyExerciseService,
                grid,
                this,
                navbarAdmin,
                createVocabularyExerciseComponent);
        this.modificationComponentDisplayed = editVocabularyExerciseComponent;
        this.removeAll();
        this.displayUIComponents();
    }
    private void displayUIComponents(){
        this.add(navbarAdmin, modificationComponentDisplayed, grid);
    }
}
