package com.example.application.views.user.reading;

import com.example.application.domain.ExerciseReading;
import com.example.application.domain.Reading;
import com.example.application.service.*;
import com.example.application.views.navbar.NavbarView;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.VaadinSession;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ReadingExercisesMain extends VerticalLayout {
    public ReadingExercisesMain(ReadingService readingService,
                                AnswersService answersService,
                                UserService userService,
                                AnswerReadingService answerReadingService,
                                ReadingExercisesService readingExercisesService){
        NavbarView navbarView = new NavbarView();
        this.add(navbarView);
        this.setWidth("100%");
        this.setAlignItems(Alignment.CENTER);
        //create wrapper object
        VerticalLayout wrapper = new VerticalLayout();
        wrapper.setAlignItems(Alignment.CENTER);
        wrapper.setWidth("80%");
        String readingId = VaadinSession.getCurrent().getAttribute("readingId").toString();
        Reading reading = readingService.findReadingById(readingId.trim());
        //display the text
        VerticalLayout text = new VerticalLayout();
        text.getStyle().set("box-shadow", "0 4px 10px 0 rgba(0,0,0,0.2), 0 4px 20px 0 rgba(0,0,0,0.19)");
        text.setWidth("80%");
        text.setPadding(true);
        List<String> paragraphs = Arrays.asList(reading.getText().split("/"));
        for(String paragraph : paragraphs) {
            Label textLabel = new Label(paragraph);
            text.add(textLabel);
        }
        this.add(text);
        //display questions and answers
        if(reading.getExercisesReading().size()==0) this.add(new Label("Не знайдено жодного завдання, скоріш за все цей курс ще не закінчено"))
                ;
        else{
            for (ExerciseReading readingExercise : reading.getExercisesReading().stream().sorted(Comparator.comparing(ExerciseReading::getId)).collect(Collectors.toList())){
                wrapper.add(new ReadingExerciseComponent(readingExercise, answersService,answerReadingService,userService, readingExercisesService));
            }
        }
        this.add(wrapper);
    }
}
