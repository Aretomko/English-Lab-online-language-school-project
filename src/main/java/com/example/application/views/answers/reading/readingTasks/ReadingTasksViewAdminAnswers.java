package com.example.application.views.answers.reading.readingTasks;

import com.example.application.domain.Lesson;
import com.example.application.domain.Reading;
import com.example.application.service.LessonsService;
import com.example.application.views.admin.NavbarAdmin;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.VaadinSession;

public class ReadingTasksViewAdminAnswers extends VerticalLayout {
    public ReadingTasksViewAdminAnswers(LessonsService lessonsService,
                                        CreateReadingTasksAdminAnswersGridService createReadingTasksAdminAnswersGridService) {
        //create navbar
        NavbarAdmin navbarAdmin = new NavbarAdmin();
        this.add(navbarAdmin);
        //find lesson
        String lessonId = VaadinSession.getCurrent().getAttribute("lessonId").toString();
        Lesson lesson = lessonsService.findLessonById(lessonId);
        //create explanation label
        Label label = new Label("All reading tasks for lesson: "+lesson.getName());
        this.add(label);
        Grid<Reading> grid = createReadingTasksAdminAnswersGridService.createReadingTaskGridByLesson(lesson);
        this.add(grid);
    }
}
