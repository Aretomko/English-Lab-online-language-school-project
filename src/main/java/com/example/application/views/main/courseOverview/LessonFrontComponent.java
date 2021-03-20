package com.example.application.views.main.courseOverview;

import com.example.application.domain.Lesson;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.VaadinSession;


public class LessonFrontComponent extends VerticalLayout {
    public LessonFrontComponent(Lesson lesson){
        Label nameLabel = new Label(lesson.getName());
        Label descriptionLabel = new Label(lesson.getDescription());
        Button button = new Button("Перейти до уроку", event->{
            VaadinSession.getCurrent().setAttribute("lessonId", lesson.getId());
            UI.getCurrent().navigate("lesson");
        });
        this.setWidth("80%");
        this.getStyle().set("box-shadow", "0 4px 10px 0 rgba(0,0,0,0.2), 0 4px 20px 0 rgba(0,0,0,0.19)");
        this.add(nameLabel, descriptionLabel, button);
    }

}
