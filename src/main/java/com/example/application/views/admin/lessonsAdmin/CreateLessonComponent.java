package com.example.application.views.admin.lessonsAdmin;

import com.example.application.domain.Lesson;
import com.example.application.domain.User;
import com.example.application.service.CourseService;
import com.example.application.service.LessonsService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;

public class CreateLessonComponent extends HorizontalLayout {
    public CreateLessonComponent(LessonsService lessonsService, CourseService courseService, Grid<Lesson> grid){
        Label editLabel = new Label("Create new lesson");
        editLabel.setHeight("wrap-content");
        editLabel.getStyle().set("font","400 13.3333px Arial");
        editLabel.getStyle().set("font-size", "var(--lumo-font-size-s)");
        editLabel.getStyle().set("font-weight", "500");
        //create form field
        TextField lessonNameTextField = new TextField("name");
        TextField lessonDescriptionTextField = new TextField("description");
        TextField lessonLinkTextField = new TextField("GDrive link");
        TextField meetingLink = new TextField("link ot the meeting");
        Select<String> placeholderSelect = new Select<>();
        placeholderSelect.setItems(courseService.getAllCoursesNames());
        placeholderSelect.setPlaceholder("not selected");
        placeholderSelect.setLabel("course");
        //create button
        Button submit = new Button("Create" , event -> {
            lessonsService.save(new Lesson(lessonNameTextField.getValue(),lessonDescriptionTextField.getValue(), lessonLinkTextField.getValue(), courseService.getCourseByName(placeholderSelect.getValue()),meetingLink.getValue()));
            grid.setItems(lessonsService.getAllLessons());
            lessonNameTextField.setValue("");
            lessonDescriptionTextField.setValue("");
            lessonLinkTextField.setValue("");
            placeholderSelect.setValue("");
        });
        this.add(editLabel , lessonNameTextField,lessonDescriptionTextField,lessonLinkTextField, meetingLink, placeholderSelect, submit);
        this.setWidth("100%");
        this.setPadding(true);
        this.setAlignItems(Alignment.BASELINE);
    }
}
