package com.example.application.views.admin.lessonsAdmin;

import com.example.application.domain.Lesson;
import com.example.application.domain.User;
import com.example.application.service.*;
import com.example.application.views.admin.NavbarAdmin;
import com.example.application.views.admin.usersAdmin.CreateUserComponent;
import com.example.application.views.admin.usersAdmin.EditUserComponent;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import javax.persistence.Entity;

@Entity
public class LessonsAdminView extends VerticalLayout {
    private final LessonsService lessonsService;
    private final CourseService courseService;
    //UI elements
    private NavbarAdmin navbarAdmin;
    private Grid<Lesson> grid;
    private HorizontalLayout modificationComponent;

    public LessonsAdminView(CreateGridLessonsService createGridLessonsService, LessonsService lessonsService, CourseService courseService){
        //Services initialization
        this.lessonsService = lessonsService;
        this.courseService = courseService;
        //UI initialization
        this.navbarAdmin = new NavbarAdmin();
        this.grid = createGridLessonsService.createGridLessons();
        CreateLessonComponent createLessonComponent = new CreateLessonComponent(lessonsService, courseService, grid);
        this.modificationComponent = createLessonComponent;
        this.grid.addItemClickListener(item -> editLessonEvent(item.getItem(), createLessonComponent ));
        this.showUI();
    }
    private void editLessonEvent(Lesson lesson, CreateLessonComponent createLessonComponent) {
        EditLessonComponent editUserComponent = new EditLessonComponent(lesson, this.courseService, this.lessonsService, this.grid, this, this.navbarAdmin, createLessonComponent);
        this.modificationComponent = editUserComponent;
        this.removeAll();
        this.showUI();
    }

    public void showUI(){
        this.add(navbarAdmin,modificationComponent, grid);
    }
}
