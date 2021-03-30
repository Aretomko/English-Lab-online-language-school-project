package com.example.application.views.answers;

import com.example.application.domain.Exercise;
import com.example.application.domain.Lesson;
import com.example.application.service.CreateAdminGridService;
import com.example.application.service.LessonsService;
import com.example.application.views.admin.NavbarAdmin;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.VaadinSession;

public class QuestionTypesViewAnswersAdmin extends VerticalLayout {
    public QuestionTypesViewAnswersAdmin(LessonsService lessonsService,
                                         CreateAdminGridService createAdminGridService){
        //add navbar
        NavbarAdmin navbarAdmin = new NavbarAdmin();
        this.add(navbarAdmin);
        //get lesson object
        String lessonId = VaadinSession.getCurrent().getAttribute("lessonId").toString();
        Lesson lesson = lessonsService.findLessonById(lessonId);
        //create grid to show possible types of exercises
        Grid<Exercise> exerciseTypesGrid = createAdminGridService.createQuestionTypesGridAnswersAdmin(lesson);
        //create explanation label
        Label explanationLabel = new Label("Now you see all exercise types for lesson - " + lesson.getName());
        this.add(explanationLabel, exerciseTypesGrid);
    }
}
