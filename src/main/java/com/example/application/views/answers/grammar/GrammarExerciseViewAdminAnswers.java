package com.example.application.views.answers.grammar;

import com.example.application.domain.ExerciseGrammar;
import com.example.application.domain.Lesson;
import com.example.application.service.CreateAdminGridService;
import com.example.application.service.LessonsService;
import com.example.application.views.admin.NavbarAdmin;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.VaadinSession;

public class GrammarExerciseViewAdminAnswers extends VerticalLayout {
    public GrammarExerciseViewAdminAnswers(LessonsService lessonsService,
                                           CreateAnswersViewAnswersGridService createAnswersViewAnswersGridService){
        //create navbar
        NavbarAdmin navbarAdmin = new NavbarAdmin();
        this.add(navbarAdmin);
        //find lesson
        String lessonId = VaadinSession.getCurrent().getAttribute("lessonId").toString();
        Lesson lesson = lessonsService.findLessonById(lessonId);
        //create explanation label
        Label label = new Label("All grammar exercises for lesson: "+lesson.getName());
        this.add(label);
        //create grid with all grammarExercises related to the lesson
        Grid<ExerciseGrammar> grid = createAnswersViewAnswersGridService.createExerciseGrammarGridByLesson(lesson);
        this.add(grid);
    }
}
