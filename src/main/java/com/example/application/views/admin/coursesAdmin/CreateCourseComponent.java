package com.example.application.views.admin.coursesAdmin;

import com.example.application.domain.Course;
import com.example.application.domain.Team;
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
    private final Grid<Course> grid;
    private final CourseService courseService;

    //create UI components
    private Label editLabel;
    private TextField courseNameTextField;
    private Button submit;

    public CreateCourseComponent(CourseService courseService, Grid grid){
        //dependencies initialization
        this.grid = grid;
        this.courseService = courseService;
        //UI initialization
        this.editLabel = new Label("Create new course");
        this.editLabel.setHeight("wrap-content");
        this.editLabel.getStyle().set("font","400 13.3333px Arial");
        this.editLabel.getStyle().set("font-size", "var(--lumo-font-size-s)");
        this.editLabel.getStyle().set("font-weight", "500");
        //create form field
        this.courseNameTextField = new TextField("course name");
        //create button
        this.submit = new Button("Create" , event -> {
            this.createCourse();
        });
        this.add(editLabel , courseNameTextField, submit);
        this.setWidth("100%");
        this.setPadding(true);
        this.setAlignItems(Alignment.BASELINE);
    }

    public void createCourse(){
        courseService.save(new Course(courseNameTextField.getValue()));
        grid.setItems(courseService.getAllCourses());
        courseNameTextField.setValue("");
    }

    public Label getEditLabel() {
        return editLabel;
    }

    public TextField getCourseNameTextField() {
        return courseNameTextField;
    }

    public Button getSubmit() {
        return submit;
    }
}
