package com.example.application.views.main.courseOverview;

import com.example.application.domain.Course;
import com.example.application.domain.Lesson;
import com.example.application.domain.Team;
import com.example.application.domain.User;
import com.example.application.service.CourseService;
import com.example.application.service.UserService;
import com.example.application.views.navbar.NavbarView;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.VaadinSession;

import java.util.Comparator;
import java.util.stream.Collectors;

public class CourseOverViewMain extends VerticalLayout {
    public CourseOverViewMain(UserService userService, CourseService courseService){
        //create navbar
        NavbarView navbarView = new NavbarView();
        this.add(navbarView);
        String username = VaadinSession.getCurrent().getAttribute("username").toString();
        User user = userService.getUserByUsername(username);
        Team team = null;
        Course course = null;
        if (user.getTeam() != null) team = user.getTeam();
        if (team!=null && team.getCourse()!= null) course = team.getCourse();
        //create wrapper layout
        VerticalLayout wrapper = new VerticalLayout();
        wrapper.setAlignItems(Alignment.CENTER);
        if(course.getLessons().size()==0) {
            this.add(new Label("Sorry this course is not co completed yet"));
        }
        else {
            for (Lesson lesson : course.getLessons().stream().sorted(Comparator.comparing(Lesson::getId)).collect(Collectors.toList())) {
                wrapper.add(new LessonFrontComponent(lesson));
            }
        }
        this.add(wrapper);
    }


}
