package com.example.application.views.user.vocabulary;

import com.example.application.domain.ExerciseVocabulary;
import com.example.application.domain.Lesson;
import com.example.application.service.*;
import com.example.application.views.navbar.NavbarView;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.VaadinSession;

import java.util.Comparator;
import java.util.List;
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

        boolean isHomework = (boolean) VaadinSession.getCurrent().getAttribute("homework");
        List<ExerciseVocabulary> exerciseToDisplay;
        if(!isHomework){
            exerciseToDisplay = lesson.getExercisesVocabulary().stream()
                    .filter(i->!i.getHomework())
                    .sorted(Comparator.comparing(ExerciseVocabulary::getId))
                    .collect(Collectors.toList());
        }else{
            exerciseToDisplay = lesson.getExercisesVocabulary().stream()
                    .filter(i->i.getHomework())
                    .sorted(Comparator.comparing(ExerciseVocabulary::getId))
                    .collect(Collectors.toList());
        }

        if(exerciseToDisplay.size()==0) this.add(new Label("В цьому уроці немає завдань з словництва"))
                ;
        else{
            for (ExerciseVocabulary exercise : exerciseToDisplay){
                wrapper.add(new VocabularyExerciseComponent(exercise, vocabularyExerciseService, userService, answerVocabularyService, answersService));
            }
        }
        this.add(wrapper);
    }
}
