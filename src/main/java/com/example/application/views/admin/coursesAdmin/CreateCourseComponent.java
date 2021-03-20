package com.example.application.views.admin.coursesAdmin;

import com.example.application.domain.Course;
import com.example.application.domain.User;
import com.example.application.service.CourseService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;

public class CreateCourseComponent extends HorizontalLayout {
    public CreateCourseComponent(CourseService courseService, Grid grid){
        Label editLabel = new Label("Create new course");
        editLabel.setHeight("wrap-content");
        editLabel.getStyle().set("font","400 13.3333px Arial");
        editLabel.getStyle().set("font-size", "var(--lumo-font-size-s)");
        editLabel.getStyle().set("font-weight", "500");
        //create form field
        TextField courseNameTextField = new TextField("course name");
        //create button
        Button submit = new Button("Create" , event -> {
            courseService.save(new Course(courseNameTextField.getValue()));
            grid.setItems(courseService.getAllCourses());
            courseNameTextField.setValue("");
        });
        this.add(editLabel , courseNameTextField, submit);
        this.setWidth("100%");
        this.setPadding(true);
        this.setAlignItems(Alignment.BASELINE);
    }
}
