package com.example.application.views.admin.exercises.readingTasks;

import com.example.application.domain.Reading;
import com.example.application.service.LessonsService;
import com.example.application.service.ReadingService;
import com.example.application.views.admin.NavbarAdmin;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

public class EditReadingTaskComponent extends VerticalLayout {
    public EditReadingTaskComponent(Reading reading,
                                    LessonsService lessonsService,
                                    ReadingService readingService,
                                    Grid<Reading> grid,
                                    ReadingTasksAdminView readingTasksAdminView,
                                    NavbarAdmin navbarAdmin,
                                    CreateReadingTaskComponent createReadingTaskComponent){
        Label editLabel = new Label("Edit group " + reading.getName());
        editLabel.setHeight("wrap-content");
        editLabel.getStyle().set("font","400 13.3333px Arial");
        editLabel.getStyle().set("font-size", "var(--lumo-font-size-s)");
        editLabel.getStyle().set("font-weight", "500");
        //create form field
        TextField nameTextField = new TextField("name");
        TextField lessonIdTextField = new TextField("lessonId");
        if (reading.getName()!= null) nameTextField.setValue(reading.getName())
                ;
        else nameTextField.setPlaceholder("not set")
                ;
        if (reading.getLesson()!=null) lessonIdTextField.setValue(String.valueOf(reading.getLesson().getId()))
                ;
        else lessonIdTextField.setPlaceholder("not set")
                ;
        //create button
        Button submit = new Button("Update" , event -> {
            reading.setName(nameTextField.getValue());
            reading.setLesson(lessonsService.findLessonById(lessonIdTextField.getValue()));
            readingService.save(reading);
            grid.getDataProvider().refreshItem(reading);
            readingTasksAdminView.removeAll();
            readingTasksAdminView.add(navbarAdmin, createReadingTaskComponent, grid);
        });
        this.add(editLabel ,nameTextField, lessonIdTextField, submit);
        this.setWidth("100%");
        this.setPadding(true);
        this.setAlignItems(Alignment.BASELINE);
    }
}
