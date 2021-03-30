package com.example.application.views.answers.listening.listenitngExercises;

import com.example.application.domain.ExerciseListening;
import com.example.application.domain.ExerciseReading;
import com.example.application.domain.Listening;
import com.example.application.domain.Reading;
import com.example.application.service.ListeningService;
import com.example.application.views.admin.NavbarAdmin;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.VaadinSession;

public class ListeningExercisesViewAdminAnswers extends VerticalLayout {
    public ListeningExercisesViewAdminAnswers(ListeningService listeningService,
                                              CreateListeningExercisesAdminAnswersGridService createListeningExercisesAdminAnswersGridService){
        //create navbar
        NavbarAdmin navbarAdmin = new NavbarAdmin();
        this.add(navbarAdmin);
        //find lesson
        String listeningId = VaadinSession.getCurrent().getAttribute("listeningId").toString();
        Listening listening = listeningService.findListeningById(listeningId);
        //create explanation label
        Label label = new Label("All reading exercises for reading task: "+ listening.getName());
        this.add(label);
        Grid<ExerciseListening> grid = createListeningExercisesAdminAnswersGridService.createListeningExercisesGridByListening(listening);
        this.add(grid);
    }
}
