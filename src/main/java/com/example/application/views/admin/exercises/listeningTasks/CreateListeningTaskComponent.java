package com.example.application.views.admin.exercises.listeningTasks;

import com.example.application.domain.Listening;
import com.example.application.domain.User;
import com.example.application.service.LessonsService;
import com.example.application.service.ListeningService;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;

public class CreateListeningTaskComponent extends HorizontalLayout {
    public CreateListeningTaskComponent(ListeningService listeningService, LessonsService lessonsService, Grid<Listening> grid){
        Label editLabel = new Label("Create new listening task");
        editLabel.setHeight("wrap-content");
        editLabel.getStyle().set("font","400 13.3333px Arial");
        editLabel.getStyle().set("font-size", "var(--lumo-font-size-s)");
        editLabel.getStyle().set("font-weight", "500");
        //create form field
        TextField userNameTextField = new TextField("name");
        TextField lessonIdTextField = new TextField("lesson id");
        //create button
        Button submit = new Button("Create" , event -> {
            listeningService.save(new Listening(userNameTextField.getValue(), lessonsService.findLessonById(lessonIdTextField.getValue())));
            grid.setItems(listeningService.getAllListings());
            userNameTextField.setValue("");
            lessonIdTextField.setValue("");
        });
        this.add(editLabel ,userNameTextField, lessonIdTextField, submit);
        this.setWidth("100%");
        this.setPadding(true);
        this.setAlignItems(Alignment.BASELINE);
    }
}
