package com.example.application.views.admin.exercises.readingTasks;

import com.example.application.domain.Reading;
import com.example.application.service.LessonsService;
import com.example.application.service.ReadingService;
import com.example.application.views.admin.NavbarAdmin;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;

public class EditReadingTaskComponent extends HorizontalLayout {

    private Label editLabel;
    private TextField readingName;
    private TextField lessonId;
    private Checkbox isHomework;
    private Button submit;

    public EditReadingTaskComponent(Reading reading,
                                    LessonsService lessonsService,
                                    ReadingService readingService,
                                    Grid<Reading> grid,
                                    ReadingTasksAdminView readingTasksAdminView,
                                    NavbarAdmin navbarAdmin,
                                    CreateReadingTaskComponent createReadingTaskComponent){
        this.editLabel = new Label("Edit group " + reading.getName());
        editLabel.setHeight("wrap-content");
        editLabel.getStyle().set("font","400 13.3333px Arial");
        editLabel.getStyle().set("font-size", "var(--lumo-font-size-s)");
        editLabel.getStyle().set("font-weight", "500");
        //create form field
        this.readingName = new TextField("name");
        this.lessonId = new TextField("lessonId");
        this.isHomework = new Checkbox("is homework");
        if (reading.getName()!= null) readingName.setValue(reading.getName())
                ;
        else readingName.setPlaceholder("not set")
                ;
        if (reading.getLesson()!=null) lessonId.setValue(String.valueOf(reading.getLesson().getId()))
                ;
        else lessonId.setPlaceholder("not set")
                ;
        if (reading.getHomework()!=null) isHomework.setValue(reading.getHomework())
                ;
        else isHomework.setValue(false)
                ;
        //create button
        Button submit = new Button("Update" , event -> {
            reading.setName(readingName.getValue());
            reading.setLesson(lessonsService.findLessonById(lessonId.getValue()));
            reading.setHomework(isHomework.getValue());
            readingService.save(reading);
            grid.getDataProvider().refreshItem(reading);
            readingTasksAdminView.removeAll();
            readingTasksAdminView.add(navbarAdmin, createReadingTaskComponent, grid);
        });
        this.add(editLabel ,readingName, lessonId, isHomework, submit);
        this.setWidth("100%");
        this.setPadding(true);
        this.setAlignItems(Alignment.BASELINE);
    }
}
