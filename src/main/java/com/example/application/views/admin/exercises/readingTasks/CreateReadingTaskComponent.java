package com.example.application.views.admin.exercises.readingTasks;

import com.example.application.domain.Reading;
import com.example.application.service.LessonsService;
import com.example.application.service.ReadingService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;

public class CreateReadingTaskComponent extends HorizontalLayout {
    public CreateReadingTaskComponent(ReadingService readingService, LessonsService lessonsService, Grid<Reading> grid){
        Label editLabel = new Label("Create new Reading task");
        editLabel.setHeight("wrap-content");
        editLabel.getStyle().set("font","400 13.3333px Arial");
        editLabel.getStyle().set("font-size", "var(--lumo-font-size-s)");
        editLabel.getStyle().set("font-weight", "500");
        //create form fields
        TextField name = new TextField("task name");
        TextField text = new TextField("text");
        TextField lessonId = new TextField("lesson id!!");

        //create button
        Button submit = new Button("Create" , event -> {
            try {
                readingService.save(new Reading(name.getValue(), text.getValue(), lessonsService.findLessonById(lessonId.getValue())));
            }catch (RuntimeException ex){
                this.add(new Label("Error, wrong id provided"));
            }
            grid.setItems(readingService.getAllReadings());
            name.setValue("");
            text.setValue("");
            lessonId.setValue("");
        });
        this.add(editLabel ,name, text, lessonId, submit);
        this.setWidth("100%");
        this.setPadding(true);
        this.setAlignItems(Alignment.BASELINE);
    }
}
