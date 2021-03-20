package com.example.application.views.main.grammarOverview;

import com.example.application.domain.ExerciseGrammar;
import com.example.application.domain.Lesson;
import com.example.application.service.*;
import com.example.application.views.navbar.NavbarView;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.VaadinSession;

import java.util.Comparator;
import java.util.stream.Collectors;

public class GrammarOverviewMain extends VerticalLayout {
    public GrammarOverviewMain(LessonsService lessonsService,
                               AnswersService answersService,
                               UserService userService,
                               AnswerGrammarService answerGrammarService,
                               GrammarExerciseService grammarExerciseService){
        this.setWidth("100%");
        this.setAlignItems(Alignment.CENTER);
        NavbarView navbarView = new NavbarView();
        this.add(navbarView);
        //create wrapper object
        VerticalLayout wrapper = new VerticalLayout();
        wrapper.setAlignItems(Alignment.CENTER);
        wrapper.setWidth("80%");
        String lessonId = VaadinSession.getCurrent().getAttribute("lessonId").toString();
        Lesson lesson = lessonsService.findLessonById(lessonId);
        if(lesson.getExercisesGrammar().size()==0) this.add(new Label("В цьому уроці немає завдань з граматики"))
                ;
        else{
            for(ExerciseGrammar exercise : lesson.getExercisesGrammar().stream().sorted(Comparator.comparing(ExerciseGrammar::getId)).collect(Collectors.toList())){
                wrapper.add(new GrammarExerciseComponent(exercise, answersService, answerGrammarService,grammarExerciseService
                        , userService));
            }
        }
        this.add(wrapper);
    }
}
