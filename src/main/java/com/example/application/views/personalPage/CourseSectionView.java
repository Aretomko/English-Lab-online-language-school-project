package com.example.application.views.personalPage;

import com.example.application.domain.Course;
import com.example.application.domain.Team;
import com.example.application.domain.User;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class CourseSectionView extends VerticalLayout {
    public CourseSectionView(User user, Team team, Course course){
        //create layout
        VerticalLayout innerLayout = new VerticalLayout();
        innerLayout.setMargin(true);
        innerLayout.setWidth("100%");
        innerLayout.getStyle().set("box-shadow", "0 4px 10px 0 rgba(0,0,0,0.2), 0 4px 20px 0 rgba(0,0,0,0.19)");
        //create elements
        Label courseLabel = new Label("Ще не встановлений");;
        Label teamLabel = new Label("Ще не встановлений");
        if(course != null) courseLabel = new Label(course.getName())
                ;
        if (team != null) teamLabel = new Label(team.getSchedule())
                ;
        Button button = new Button("Перейти до курсу", event->{
            UI.getCurrent().navigate("course");
        });
        innerLayout.add(courseLabel, teamLabel, button);
        add(innerLayout);
        this.setWidth("70%");
        UI.getCurrent().getPage().retrieveExtendedClientDetails(receiver -> {
            int screenWidth = receiver.getScreenWidth();
            if (screenWidth < 500) {
                this.setWidth("100%");
            }}
            );
    }
}
