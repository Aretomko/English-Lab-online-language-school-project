package com.example.application.views.admin.coursesAdmin;

import com.example.application.domain.Course;
import com.example.application.service.CourseService;
import com.example.application.service.TeamService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.data.provider.ListDataProvider;
import org.springframework.stereotype.Service;

@Service
public class CreateGridCoursesService {
    private final CourseService courseService;
    private final TeamService teamService;

    public CreateGridCoursesService(CourseService courseService, TeamService teamService) {
        this.courseService = courseService;
        this.teamService = teamService;
    }

    public Grid<Course> createGridCourses(){
        Grid<Course> grid = new Grid<>();
        grid.setItems(courseService.getAllCourses());
        grid.addColumn(Course::getName).setHeader("Course name");
        grid.addComponentColumn(item -> createRemoveButtonCourses(grid, item, teamService)).setHeader("Delete team");
        return grid;
    }

    private Button createRemoveButtonCourses(Grid<Course> grid, Course item, TeamService teamService) {
        @SuppressWarnings("unchecked")
        Button button = new Button("Delete", clickEvent -> {
            ListDataProvider<Course> dataProvider = (ListDataProvider<Course>) grid.getDataProvider();
            dataProvider.getItems().remove(item);
            courseService.delete(item);
            dataProvider.refreshAll();
        });
        return button;
    }
}
