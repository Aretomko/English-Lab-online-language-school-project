package com.example.application.views.answers.listening.listeningTasks;

import com.example.application.domain.Lesson;
import com.example.application.domain.Listening;
import com.example.application.service.LessonsService;
import com.example.application.views.admin.NavbarAdmin;
import com.example.application.views.answers.listening.listeningTasks.CreateListeningTaskAdminAnswersGridService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.VaadinSession;

public class ListeningTasksViewAdminAnswers extends VerticalLayout {
    public ListeningTasksViewAdminAnswers(LessonsService lessonsService,
                                          CreateListeningTaskAdminAnswersGridService createListeningTaskAdminAnswersGridService){
        //create navbar
        NavbarAdmin navbarAdmin = new NavbarAdmin();
        this.add(navbarAdmin);
        //find lesson
        String lessonId = VaadinSession.getCurrent().getAttribute("lessonId").toString();
        Lesson lesson = lessonsService.findLessonById(lessonId);
        //create explanation label
        Label label = new Label("All listening tasks for lesson: "+lesson.getName());
        this.add(label);
        Grid<Listening> grid = createListeningTaskAdminAnswersGridService.createReadingTaskGridByLesson(lesson);
        this.add(grid);
    }
}
