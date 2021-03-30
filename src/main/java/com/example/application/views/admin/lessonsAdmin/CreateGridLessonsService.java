package com.example.application.views.admin.lessonsAdmin;

import com.example.application.domain.Lesson;
import com.example.application.service.CourseService;
import com.example.application.service.LessonsService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.data.provider.ListDataProvider;
import org.springframework.stereotype.Service;

@Service
public class CreateGridLessonsService {
    private final LessonsService lessonsService;
    private final CourseService courseService;

    public CreateGridLessonsService(LessonsService lessonsService, CourseService courseService) {
        this.lessonsService = lessonsService;
        this.courseService = courseService;
    }

    public Grid<Lesson> createGridLessons(){
        Grid<Lesson> grid = new Grid<>();
        grid.setItems(lessonsService.getAllLessons());
        grid.addColumn(Lesson::getId).setHeader("Lesson id");
        grid.addColumn(Lesson::getName).setHeader("Lesson name");
        grid.addColumn(Lesson::getDescription).setHeader("Description");
        grid.addColumn(Lesson::getMeetingLink).setHeader("Meeting link");
        grid.addColumn(Lesson::getFilesLink).setHeader("GDrive link");
        try {
            grid.addColumn(item -> courseService.getCourseNameByLesson(item)).setHeader("Course");
        }catch(Exception ex){
            grid.addColumn("no course yet").setHeader("Course");
        }
        grid.addComponentColumn(item-> createRemoveButtonLessons(grid, item,lessonsService)).setHeader("Delete lesson");
        return grid;
    }

    private Button createRemoveButtonLessons(Grid<Lesson> grid, Lesson lesson, LessonsService lessonsService) {
        @SuppressWarnings("unchecked")
        Button button = new Button("Delete", clickEvent -> {
            ListDataProvider<Lesson> dataProvider = (ListDataProvider<Lesson>) grid.getDataProvider();
            dataProvider.getItems().remove(lesson);
            lessonsService.delete(lesson);
            dataProvider.refreshAll();
        });
        return button;
    }

}
