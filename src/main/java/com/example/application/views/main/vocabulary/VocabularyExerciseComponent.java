package com.example.application.views.main.vocabulary;

import com.example.application.domain.AnswerVocabulary;
import com.example.application.domain.ExerciseVocabulary;
import com.example.application.domain.User;
import com.example.application.service.AnswerVocabularyService;
import com.example.application.service.AnswersService;
import com.example.application.service.UserService;
import com.example.application.service.VocabularyExerciseService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.server.VaadinSession;


public class VocabularyExerciseComponent extends VerticalLayout {
    public VocabularyExerciseComponent(ExerciseVocabulary exerciseVocabulary, VocabularyExerciseService vocabularyExerciseService, UserService userService, AnswerVocabularyService answerVocabularyService, AnswersService answersService){
        this.getStyle().set("box-shadow", "0 4px 10px 0 rgba(0,0,0,0.2), 0 4px 20px 0 rgba(0,0,0,0.19)");
        this.setWidth("100%");
        this.setPadding(true);
        //get lodged-in user
        String username = VaadinSession.getCurrent().getAttribute("username").toString();
        User user = userService.getUserByUsername(username);
        boolean answered =answersService.isAnsweredExerciseVocabulary(exerciseVocabulary, user);
        answersService.changeColorIfAnsweredExerciseVocabulary(answered,exerciseVocabulary,this, user);
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setWidth("100%");
        Label text = new Label(exerciseVocabulary.getText());
        text.getStyle().set("font-size", "25px");
        TextField answer= new TextField();
        answer.setPlaceholder("Відповідь");
        horizontalLayout.add(text);
        //create answer button
        Button answerButton = new Button("Відповісти", event->{
                //check if the answer is correct or not
                boolean isCorrect =exerciseVocabulary.getRightAnswer().equals(answer.getValue().trim());
                AnswerVocabulary answerVocabulary = new AnswerVocabulary(answer.getValue(), exerciseVocabulary.getRightAnswer(), isCorrect, exerciseVocabulary, user);
                answerVocabularyService.save(answerVocabulary);
                exerciseVocabulary.addAnswerVocabulary(answerVocabulary);
                vocabularyExerciseService.save(exerciseVocabulary);
                answersService.changeColorIfAnsweredExerciseVocabulary(true, exerciseVocabulary, this, user);
                this.removeAll();
                this.add(text);
                this.add(new Label("Правильна відповідь - "+ exerciseVocabulary.getRightAnswer()));
        });
        this.add(horizontalLayout);
        if (!answered){
            horizontalLayout.add(answer);
            this.add(answerButton);
        }else{
            this.add(horizontalLayout, new Label("Правильна відповідь - "+ exerciseVocabulary.getRightAnswer()));
        }
    }
}
