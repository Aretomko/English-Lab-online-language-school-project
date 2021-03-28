package com.example.application.views.user.listening;

import com.example.application.domain.Listening;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.VaadinSession;

public class ListeningTaskComponent extends VerticalLayout {
    public ListeningTaskComponent(Listening listening){
        this.getStyle().set("box-shadow", "0 4px 10px 0 rgba(0,0,0,0.2), 0 4px 20px 0 rgba(0,0,0,0.19)");
        this.setWidth("100%");
        this.setPadding(true);
        //display task name
        Label taskName = new Label(listening.getName());
        Button seeListeningExercisesButton = new Button("Розпочати завдвння", event->{
            VaadinSession.getCurrent().setAttribute("listeningId", listening.getId());
            UI.getCurrent().navigate("lesson/listening/exercises");
        });
        this.add(taskName, seeListeningExercisesButton);
    }
}
