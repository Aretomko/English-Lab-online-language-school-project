package com.example.application.views.user.reading;

import com.example.application.domain.Lesson;
import com.example.application.domain.Reading;
import com.example.application.service.LessonsService;
import com.example.application.views.navbar.NavbarView;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.VaadinSession;

import java.util.Comparator;
import java.util.stream.Collectors;

public class ReadingTasksMain extends VerticalLayout {
    //reading task contains reading exercises, task connected to the lesson, exercises connected to the task
    public ReadingTasksMain(LessonsService lessonsService){
        NavbarView navbarView = new NavbarView();
        this.add(navbarView);
        this.setWidth("100%");
        this.setAlignItems(Alignment.CENTER);
        //create wrapper object
        VerticalLayout wrapper = new VerticalLayout();
        wrapper.setAlignItems(Alignment.CENTER);
        wrapper.setWidth("80%");
        String lessonId = VaadinSession.getCurrent().getAttribute("lessonId").toString();
        Lesson lesson = lessonsService.findLessonById(lessonId);
        if(lesson.getReading().size()==0) this.add(new Label("В цьому уроці немає завдань з читання"))
                ;
        else{
            for (Reading task : lesson.getReading().stream().sorted(Comparator.comparing(Reading::getId)).collect(Collectors.toList())){
                wrapper.add(new ReadingTaskComponent(task));
            }
        }
        this.add(wrapper);
    }
}
