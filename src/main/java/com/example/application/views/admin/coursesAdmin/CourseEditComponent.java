package com.example.application.views.admin.coursesAdmin;

import com.example.application.domain.Course;
import com.example.application.service.CourseService;
import com.example.application.views.admin.NavbarAdmin;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Nav;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;

public class CourseEditComponent extends HorizontalLayout {
    private Label editLabel;
    private TextField courseNameTextField;
    private Button submit;
    private Course course;

    public CourseEditComponent(Course course, CourseService courseService, Grid<Course> grid, CoursesAdminView adminView, NavbarAdmin navbarAdmin, CreateCourseComponent createCourseComponent){
        this.course = course;
        this.editLabel = new Label("Edit course " + course.getName());
        editLabel.setHeight("wrap-content");
        editLabel.getStyle().set("font","400 13.3333px Arial");
        editLabel.getStyle().set("font-size", "var(--lumo-font-size-s)");
        editLabel.getStyle().set("font-weight", "500");
        //create form field
        this.courseNameTextField = new TextField("course name");
        if (course.getName()!=null) courseNameTextField.setValue(course.getName())
                ;
        else courseNameTextField.setPlaceholder("not set")
                ;
        //create button
        this.submit = new Button("Update" , event -> {
            updateCourse(course, courseService, grid, adminView, navbarAdmin, createCourseComponent);
        });
        this.add(editLabel ,courseNameTextField, submit);
        this.setWidth("100%");
        this.setPadding(true);
        this.setAlignItems(Alignment.BASELINE);
    }
    public void updateCourse(Course course,
                              CourseService courseService,
                              Grid<Course> grid,
                              CoursesAdminView adminView,
                              NavbarAdmin navbarAdmin,
                              CreateCourseComponent createCourseComponent){
        this.course.setName(courseNameTextField.getValue());
        courseService.save(this.course);
        grid.getDataProvider().refreshItem(course);
        adminView.removeAll();
        adminView.add(navbarAdmin, createCourseComponent, grid);
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

    public Course getCourse() {
        return course;
    }
}
