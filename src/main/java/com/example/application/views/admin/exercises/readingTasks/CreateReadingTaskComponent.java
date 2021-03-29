package com.example.application.views.admin.exercises.readingTasks;

import com.example.application.domain.Reading;
import com.example.application.service.LessonsService;
import com.example.application.service.ReadingService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;

public class CreateReadingTaskComponent extends HorizontalLayout {
    private final ReadingService readingService;
    private final LessonsService lessonsService;
    private Grid<Reading> grid;

    private Label editLabel;
    private TextField readingName;
    private TextField lessonId;
    private Checkbox isHomework;
    private Button submit;

    public CreateReadingTaskComponent(ReadingService readingService, LessonsService lessonsService, Grid<Reading> grid){
        this.readingService = readingService;
        this.lessonsService = lessonsService;
        this.grid = grid;
        //UI initialization
        this.editLabel = new Label("Create new Reading task");
        editLabel.setHeight("wrap-content");
        editLabel.getStyle().set("font","400 13.3333px Arial");
        editLabel.getStyle().set("font-size", "var(--lumo-font-size-s)");
        editLabel.getStyle().set("font-weight", "500");
        //create form fields
        this.readingName = new TextField("task name");
        this.lessonId = new TextField("lesson id!!");
        this.isHomework = new Checkbox("is homework");
        //create button
        this.submit = new Button("Create" , event -> {
            try {
                readingService.save(new Reading(readingName.getValue(), lessonsService.findLessonById(lessonId.getValue()), isHomework.getValue()));
            }catch (RuntimeException ex){
                this.add(new Label("Error, wrong id provided"));
            }
            grid.setItems(readingService.getAllReadings());
            readingName.setValue("");
            lessonId.setValue("");
            isHomework.setValue(false);
        });
        this.add(editLabel ,readingName, lessonId,isHomework, submit);
        this.setWidth("100%");
        this.setPadding(true);
        this.setAlignItems(Alignment.BASELINE);
    }
}
