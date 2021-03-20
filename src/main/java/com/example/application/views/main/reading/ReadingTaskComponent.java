package com.example.application.views.main.reading;

import com.example.application.domain.Reading;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.VaadinSession;


public class ReadingTaskComponent extends VerticalLayout {
    public ReadingTaskComponent(Reading reading){
        this.getStyle().set("box-shadow", "0 4px 10px 0 rgba(0,0,0,0.2), 0 4px 20px 0 rgba(0,0,0,0.19)");
        this.setWidth("100%");
        this.setPadding(true);
        Label text = new Label(reading.getName());
        Button seeReadingExercisesButton = new Button("Розпочати завдвння", event->{
            VaadinSession.getCurrent().setAttribute("readingId", reading.getId());
            UI.getCurrent().navigate("lesson/reading/exercises");
        });
        this.add(text,seeReadingExercisesButton);
    }
}
