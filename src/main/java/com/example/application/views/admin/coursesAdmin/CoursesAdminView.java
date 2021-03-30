package com.example.application.views.admin.coursesAdmin;

import com.example.application.domain.Course;
import com.example.application.service.CourseService;
import com.example.application.views.admin.NavbarAdmin;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class CoursesAdminView extends VerticalLayout {
    private final CreateGridCoursesService createGridCoursesService;
    private final CourseService courseService;
    //UI elements
    private HorizontalLayout shownModificationComponent;
    private NavbarAdmin navbarAdmin;
    private Grid<Course> grid;

    public CoursesAdminView(CreateGridCoursesService createGridCoursesService, CourseService courseService){
        //initialize services
        this.createGridCoursesService = createGridCoursesService;
        this.courseService = courseService;
        //create UI items
        this.addNavbar();
        this.grid = this.createGridCoursesService.createGridCourses();
        CreateCourseComponent createUserComponent = new CreateCourseComponent(courseService, grid);
        this.shownModificationComponent = createUserComponent;
        grid.addItemClickListener(item -> editCourseEvent(item.getItem(), createUserComponent));
        this.showUi();
    }

    public void addNavbar(){
        this.navbarAdmin = new NavbarAdmin();
        add(this.navbarAdmin);
    }

    public void editCourseEvent(Course item, CreateCourseComponent createCourseComponent) {
        CourseEditComponent courseEditComponent = new CourseEditComponent(item,
                this.courseService,
                this.grid,
                this,
                this.navbarAdmin,
                createCourseComponent );
        this.removeAll();
        this.shownModificationComponent = courseEditComponent;
        this.showUi();
    }

    private void showUi(){
        this.add(navbarAdmin, shownModificationComponent, grid);
    }
}
