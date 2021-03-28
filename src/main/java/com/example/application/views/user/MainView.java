package com.example.application.views.user;

import com.example.application.domain.Course;
import com.example.application.domain.Team;
import com.example.application.domain.User;
import com.example.application.service.AuthService;
import com.example.application.service.UserService;
import com.example.application.views.navbar.NavbarView;
import com.example.application.views.personalPage.PersonalPageView;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.server.VaadinSession;

@PWA(name = "Vaadin Application",
        shortName = "Vaadin App",
        description = "This is an example Vaadin application.",
        enableInstallPrompt = false)
public class MainView extends VerticalLayout implements RouterLayout {

    public MainView(UserService getUserService, AuthService authService){
        String username = VaadinSession.getCurrent().getAttribute("username").toString();
        User user = getUserService.getUserByUsername(username);
        Team team = null;
        Course course = null;
        if (user.getTeam() != null) team = user.getTeam();
        if (team!=null && team.getCourse()!= null) course = team.getCourse();
        NavbarView navbarView = new NavbarView();
        PersonalPageView personalPageView = new PersonalPageView(user, team, course, authService);
        add(navbarView);
        add(personalPageView);
    }
}
