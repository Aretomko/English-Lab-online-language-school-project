package com.example.application.views.answers.vocabluary;

import com.example.application.domain.ExerciseGrammar;
import com.example.application.domain.ExerciseVocabulary;
import com.example.application.domain.Lesson;
import com.example.application.service.CreateAdminGridService;
import com.example.application.service.LessonsService;
import com.example.application.views.admin.NavbarAdmin;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.VaadinSession;

public class VocabularyExerciseViewAdminAnswers extends VerticalLayout {
    public VocabularyExerciseViewAdminAnswers(LessonsService lessonsService,
                                              CreateAdminGridService createAdminGridService){
        //create navbar
        NavbarAdmin navbarAdmin = new NavbarAdmin();
        this.add(navbarAdmin);
        //find lesson
        String lessonId = VaadinSession.getCurrent().getAttribute("lessonId").toString();
        Lesson lesson = lessonsService.findLessonById(lessonId);
        //create explanation label
        Label label = new Label("All vocabulary exercises for lesson: "+lesson.getName());
        Grid<ExerciseVocabulary> grid = createAdminGridService.createExerciseVocabularyGridByLesson(lesson);
        //create explanation label
        this.add(new Label("Now you see exercises for lesson: "+lesson.getName()));
        this.add(grid);
    }
}
