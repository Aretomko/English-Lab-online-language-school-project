package com.example.application.views.user.listening;

import com.example.application.domain.ExerciseListening;
import com.example.application.domain.Listening;
import com.example.application.service.*;
import com.example.application.views.navbar.NavbarView;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.VaadinSession;

import java.util.Comparator;
import java.util.stream.Collectors;

public class ListeningExercisesMain extends VerticalLayout {
    public ListeningExercisesMain(ListeningService listeningService,
                                  AnswersService answersService,
                                  UserService userService,
                                  AnswersListeningService answersListeningService,
                                  ListeningExerciseService listeningExerciseService){
        NavbarView navbarView = new NavbarView();
        this.add(navbarView);
        this.setWidth("100%");
        this.setAlignItems(Alignment.CENTER);
        //create wrapper object
        VerticalLayout wrapper = new VerticalLayout();
        wrapper.setAlignItems(Alignment.CENTER);
        wrapper.setWidth("80%");
        String listeningId = VaadinSession.getCurrent().getAttribute("listeningId").toString();
        Listening listening = listeningService.findListeningById(listeningId.trim());
        if(listening.getExerciseListening().size()==0) this.add(new Label("В цьому завданні немає запитань, скоріш за все цей курс ще створюєтся"))
                ;
        else{
            for (ExerciseListening exerciseListening :listening.getExerciseListening().stream().sorted(Comparator.comparing(ExerciseListening::getId)).collect(Collectors.toList())){
                wrapper.add(new ListeningExerciseComponent(exerciseListening, answersService, userService, answersListeningService, listeningExerciseService));
            }
        }
        this.add(wrapper);
    }
}
