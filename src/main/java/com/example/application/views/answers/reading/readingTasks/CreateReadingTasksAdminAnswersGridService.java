package com.example.application.views.answers.reading.readingTasks;

import com.example.application.domain.Lesson;
import com.example.application.domain.Reading;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.stream.Collectors;

@Service
public class CreateReadingTasksAdminAnswersGridService {
    private Grid<Reading> grid;

    public Grid<Reading> createReadingTaskGridByLesson(Lesson lesson){
        this.grid = new Grid<Reading>();
        grid.setItems(lesson.getReading().stream().sorted(Comparator.comparing(Reading::getId)).collect(Collectors.toList()));
        grid.addColumn(Reading::getName).setHeader("Reading task name");
        grid.addComponentColumn(item -> createSeeAnswersButtonByReading(item)).setHeader("See exercises");
        return grid;
    }

    public Button createSeeAnswersButtonByReading(Reading reading){
        return new Button("See exercises", event->{
            VaadinSession.getCurrent().setAttribute("readingId", reading.getId());
            UI.getCurrent().navigate("admin/answers/questions/reading");
        });
    }

    public Grid<Reading> getGrid() {
        return grid;
    }
}
