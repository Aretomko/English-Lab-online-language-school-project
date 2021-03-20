package com.example.application.views.admin;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class NavbarAdmin extends VerticalLayout {
    public NavbarAdmin(){
        HorizontalLayout centered = new HorizontalLayout();
        Button back = new com.vaadin.flow.component.button.Button("<-" , event -> {
            UI.getCurrent().navigate("personal");
        });
        Button usersButton = new com.vaadin.flow.component.button.Button("Users" , event -> {
            UI.getCurrent().navigate("admin/users");
        });
        Button teamsButton = new com.vaadin.flow.component.button.Button("Groups" , event -> {
            UI.getCurrent().navigate("admin/groups");
        });
        Button courseButton = new com.vaadin.flow.component.button.Button("Courses" , event -> {
            UI.getCurrent().navigate("admin/courses");
        });
        Button lessonsButton = new com.vaadin.flow.component.button.Button("Lessons" , event -> {
            UI.getCurrent().navigate("admin/lessons");
        });
        Button adminReadingTasksButton = new com.vaadin.flow.component.button.Button("Reading t" , event -> {
            UI.getCurrent().navigate("admin/readingTasks");
        });
        Button adminListeningTasksButton = new com.vaadin.flow.component.button.Button("Listening t" , event -> {
            UI.getCurrent().navigate("admin/listeningTasks");
        });
        Button adminReadingExercisesButton = new com.vaadin.flow.component.button.Button("Reading ex" , event -> {
            UI.getCurrent().navigate("admin/readingExercises");
        });
        Button adminListeningExercisesButton = new com.vaadin.flow.component.button.Button("Listening ex" , event -> {
            UI.getCurrent().navigate("admin/listeningExercises");
        });
        Button adminGrammarExercisesButton = new com.vaadin.flow.component.button.Button("Grammar ex" , event -> {
            UI.getCurrent().navigate("admin/grammarExercises");
        });
        Button adminVocabularyExercisesButton = new com.vaadin.flow.component.button.Button("Vocabulary ex" , event -> {
            UI.getCurrent().navigate("admin/vocabularyExercises");
        });

        this.setWidth("100%");
        this.setMinHeight("100px");
        this.setAlignItems(Alignment.CENTER);
        centered.add(back, usersButton, teamsButton, courseButton, lessonsButton, adminReadingTasksButton,adminListeningTasksButton, adminReadingExercisesButton, adminListeningExercisesButton, adminGrammarExercisesButton, adminVocabularyExercisesButton);
        this.add(centered);
    }
}
