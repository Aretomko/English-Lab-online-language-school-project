package com.example.application.views.personalPage;

import com.example.application.domain.Course;
import com.example.application.domain.Team;
import com.example.application.domain.User;
import com.example.application.service.AuthService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class PersonalPageView extends HorizontalLayout {
    public PersonalPageView(User user, Team team, Course course, AuthService authService){
        this.setWidth("80%");
        this.getStyle().set("margin", "auto");
        CourseSectionView courseSectionView = new CourseSectionView(user, team, course);
        PersonalInfoView personalInfoView = new PersonalInfoView(user, authService);
        //reduce margin on left and right side is the device id mobile
        //place elements in vertical layout if the device is mobile
        UI.getCurrent().getPage().retrieveExtendedClientDetails(receiver -> {
            int screenWidth = receiver.getScreenWidth();
            if (screenWidth < 500) {
                this.setWidth("100%");
                VerticalLayout verticalLayout = new VerticalLayout();
                verticalLayout.setWidth("100%");
                verticalLayout.add(courseSectionView,personalInfoView);
                this.add(verticalLayout);
            }else{
                this.add(courseSectionView,personalInfoView);
            }
        });

    }

}
