package com.example.application.views.user.listening;

import com.example.application.domain.Lesson;
import com.example.application.domain.Listening;
import com.example.application.service.LessonsService;
import com.example.application.views.navbar.NavbarView;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.VaadinSession;

import java.util.Comparator;
import java.util.stream.Collectors;

public class ListeningTasksMain extends VerticalLayout {
    public ListeningTasksMain(LessonsService lessonsService){
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
        if(lesson.getListening().size()==0) this.add(new Label("В цьому уроці немає завдань з аудіювання"))
                ;
        else{
            for (Listening listeningTask : lesson.getListening().stream().sorted(Comparator.comparing(Listening::getId)).collect(Collectors.toList())){
                wrapper.add(new ListeningTaskComponent(listeningTask));
            }
        }
        this.add(wrapper);
    }
}
