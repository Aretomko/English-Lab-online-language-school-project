package com.example.application.views.admin.coursesAdmin;

import com.example.application.domain.Course;
import com.example.application.domain.User;
import com.example.application.service.CourseService;
import com.example.application.service.CreateAdminGridService;
import com.example.application.service.TeamService;
import com.example.application.service.UserService;
import com.example.application.views.admin.NavbarAdmin;
import com.example.application.views.admin.usersAdmin.CreateUserComponent;
import com.example.application.views.admin.usersAdmin.EditUserComponent;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class CoursesAdminView extends VerticalLayout {
    public CoursesAdminView(CreateAdminGridService createAdminGridService, CourseService courseService){
        NavbarAdmin navbarAdmin = new NavbarAdmin();
        add(navbarAdmin);
        Grid<Course> grid = createAdminGridService.createGridCourses();
        CreateCourseComponent createUserComponent = new CreateCourseComponent(courseService, grid);
        grid.addItemClickListener(item -> editCourseEvent(item.getItem(), courseService,navbarAdmin, grid, createUserComponent));
        this.add(navbarAdmin, createUserComponent, grid);
    }
    private void editCourseEvent(Course item, CourseService courseService,NavbarAdmin navbarAdmin, Grid<Course> grid, CreateCourseComponent createCourseComponent) {
        CourseEditComponent courseEditComponent = new CourseEditComponent(item,courseService, grid, this, navbarAdmin, createCourseComponent );
        this.removeAll();
        this.add(navbarAdmin,courseEditComponent, grid);
    }
}
