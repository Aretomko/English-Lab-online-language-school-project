package com.example.application.views.admin.lessonsAdmin;

import com.example.application.domain.Lesson;
import com.example.application.domain.User;
import com.example.application.service.*;
import com.example.application.views.admin.NavbarAdmin;
import com.example.application.views.admin.usersAdmin.CreateUserComponent;
import com.example.application.views.admin.usersAdmin.EditUserComponent;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

import javax.persistence.Entity;

@Entity
public class LessonsAdminView extends VerticalLayout {
    public LessonsAdminView(CreateAdminGridService createAdminGridService, LessonsService lessonsService, CourseService courseService){
        NavbarAdmin navbarAdmin = new NavbarAdmin();
        add(navbarAdmin);
        Grid<Lesson> grid = createAdminGridService.createGridLessons();
        CreateLessonComponent createLessonComponent = new CreateLessonComponent(lessonsService, courseService, grid);
        grid.addItemClickListener(item -> editLessonEvent(item.getItem(),courseService, lessonsService, grid, navbarAdmin, createLessonComponent ));
        this.add(navbarAdmin, createLessonComponent, grid);
    }
    private void editLessonEvent(Lesson lesson, CourseService courseService, LessonsService lessonsService, Grid<Lesson> grid, NavbarAdmin navbarAdmin, CreateLessonComponent createLessonComponent) {
        EditLessonComponent editUserComponent = new EditLessonComponent(lesson, courseService, lessonsService, grid, this, navbarAdmin, createLessonComponent);
        this.removeAll();
        this.add(navbarAdmin,editUserComponent, grid);
    }
}
