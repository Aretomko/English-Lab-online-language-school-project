package com.example.application.views.admin.exercises.vocabularyExercises;

import com.example.application.domain.ExerciseVocabulary;
import com.example.application.service.CreateAdminGridService;
import com.example.application.service.LessonsService;
import com.example.application.service.VocabularyExerciseService;
import com.example.application.views.admin.NavbarAdmin;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class VocabularyExerciseAdminAdmin extends VerticalLayout {
    public VocabularyExerciseAdminAdmin(CreateAdminGridService createAdminGridService,
                                        VocabularyExerciseService vocabularyExerciseService,
                                        LessonsService lessonsService){
        NavbarAdmin navbarAdmin = new NavbarAdmin();
        add(navbarAdmin);
        Grid<ExerciseVocabulary> grid = createAdminGridService.createGridExerciseVocabulary();
        CreateVocabularyExerciseComponent createVocabularyExerciseComponent = new CreateVocabularyExerciseComponent(vocabularyExerciseService,lessonsService, grid);
        grid.addItemClickListener(item -> editVocabluaryExerciseEvent(item.getItem(),
                lessonsService,
                vocabularyExerciseService,
                grid,
                navbarAdmin,
                createVocabularyExerciseComponent));
        this.add(navbarAdmin, createVocabularyExerciseComponent, grid);
    }

    private void editVocabluaryExerciseEvent(ExerciseVocabulary item,
                                             LessonsService lessonsService,
                                             VocabularyExerciseService vocabularyExerciseService,
                                             Grid<ExerciseVocabulary> grid,
                                             NavbarAdmin navbarAdmin,
                                             CreateVocabularyExerciseComponent createVocabularyExerciseComponent) {
        EditVocabularyExerciseComponent editVocabularyExerciseComponent = new EditVocabularyExerciseComponent(item,
                lessonsService,
                vocabularyExerciseService,
                grid,
                this,
                navbarAdmin,
                createVocabularyExerciseComponent);
        this.removeAll();
        this.add(navbarAdmin,editVocabularyExerciseComponent, grid);
    }
}
