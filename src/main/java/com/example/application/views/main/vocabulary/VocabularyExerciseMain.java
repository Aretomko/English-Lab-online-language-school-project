package com.example.application.views.main.vocabulary;

import com.example.application.domain.ExerciseVocabulary;
import com.example.application.domain.Lesson;
import com.example.application.service.*;
import com.example.application.views.navbar.NavbarView;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.VaadinSession;

import java.util.Comparator;
import java.util.stream.Collectors;

public class VocabularyExerciseMain extends VerticalLayout {
    public VocabularyExerciseMain(LessonsService lessonsService, UserService userService, AnswerVocabularyService answerVocabularyService, AnswersService answersService, VocabularyExerciseService vocabularyExerciseService){
        //create navbar
        NavbarView navbarView = new NavbarView();
        this.add(navbarView);
        this.setWidth("100%");
        this.setAlignItems(Alignment.CENTER);
        //create wrapper object
        VerticalLayout wrapper = new VerticalLayout();
        wrapper.setAlignItems(Alignment.CENTER);
        wrapper.setWidth("80%");
        String lessonId = VaadinSession.getCurrent().getAttribute("lessonId").toString();
        Lesson lesson = lessonsService.findLessonById(lessonId);
        if(lesson.getExercisesVocabulary().size()==0) this.add(new Label("В цьому уроці немає завдань з словництва"))
                ;
        else{
            for (ExerciseVocabulary exercise : lesson.getExercisesVocabulary().stream().sorted(Comparator.comparing(ExerciseVocabulary::getId)).collect(Collectors.toList())){
                wrapper.add(new VocabularyExerciseComponent(exercise, vocabularyExerciseService, userService, answerVocabularyService, answersService));
            }
        }
        this.add(wrapper);
    }
}
