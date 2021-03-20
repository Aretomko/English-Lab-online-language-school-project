package com.example.application.views.admin.coursesAdmin;

import com.example.application.domain.Course;
import com.example.application.service.CourseService;
import com.example.application.views.admin.NavbarAdmin;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;

public class CourseEditComponent extends HorizontalLayout {
    public CourseEditComponent(Course course, CourseService courseService, Grid<Course> grid, CoursesAdminView adminView, NavbarAdmin navbarAdmin, CreateCourseComponent createCourseComponent){
        Label editLabel = new Label("Edit course " + course.getName());
        editLabel.setHeight("wrap-content");
        editLabel.getStyle().set("font","400 13.3333px Arial");
        editLabel.getStyle().set("font-size", "var(--lumo-font-size-s)");
        editLabel.getStyle().set("font-weight", "500");
        //create form field
        TextField userNameTextField = new TextField("course name");
        if (course.getName()!=null) userNameTextField.setValue(course.getName())
                ;
        else userNameTextField.setPlaceholder("not set")
                ;
        //create button
        Button submit = new Button("Update" , event -> {
            course.setName(userNameTextField.getValue());
            courseService.save(course);
            grid.getDataProvider().refreshItem(course);
            adminView.removeAll();
            adminView.add(navbarAdmin, createCourseComponent, grid);
        });
        this.add(editLabel ,userNameTextField, submit);
        this.setWidth("100%");
        this.setPadding(true);
        this.setAlignItems(Alignment.BASELINE);
    }
}
