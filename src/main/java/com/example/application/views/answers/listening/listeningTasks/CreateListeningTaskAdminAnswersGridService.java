package com.example.application.views.answers.listening.listeningTasks;

import com.example.application.domain.Lesson;
import com.example.application.domain.Listening;
import com.example.application.domain.Reading;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.stream.Collectors;

@Service
public class CreateListeningTaskAdminAnswersGridService {
    private Grid<Listening> grid;

    public Grid<Listening> createReadingTaskGridByLesson(Lesson lesson){
        this.grid = new Grid<Listening>();
        grid.setItems(lesson.getListening().stream().sorted(Comparator.comparing(Listening::getId)).collect(Collectors.toList()));
        grid.addColumn(Listening::getName).setHeader("Reading task name");
        grid.addComponentColumn(this::createSeeAnswersButtonByListening).setHeader("See exercises");
        return grid;
    }

    public Button createSeeAnswersButtonByListening(Listening listening){
        return new Button("See exercises", event->{
            VaadinSession.getCurrent().setAttribute("listeningId", listening.getId());
            UI.getCurrent().navigate("admin/answers/questions/listening");
        });
    }

    public Grid<Listening> getGrid() {
        return grid;
    }
}
