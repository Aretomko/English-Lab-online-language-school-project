package com.example.application.views.user.reading;

import com.example.application.domain.AnswerReading;
import com.example.application.domain.ExerciseReading;
import com.example.application.domain.User;
import com.example.application.service.AnswerReadingService;
import com.example.application.service.AnswersService;
import com.example.application.service.ReadingExercisesService;
import com.example.application.service.UserService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.server.VaadinSession;


public class ReadingExerciseComponent extends VerticalLayout {
    public ReadingExerciseComponent(ExerciseReading exerciseReading,
                                    AnswersService answersService,
                                    AnswerReadingService answerReadingService,
                                    UserService userService,
                                    ReadingExercisesService readingExercisesService){
        this.getStyle().set("box-shadow", "0 4px 10px 0 rgba(0,0,0,0.2), 0 4px 20px 0 rgba(0,0,0,0.19)");
        this.setWidth("100%");
        this.setPadding(true);
        //display question
        Label text = new Label(exerciseReading.getText());
        this.add(text);
        //find logged-in user
        String username = VaadinSession.getCurrent().getAttribute("username").toString();
        User user = userService.getUserByUsername(username);
        //display possible answers
        boolean answered = answersService.isAnsweredExerciseReading(exerciseReading, user);
        answersService.changeColorIfAnsweredExerciseReading(answered,exerciseReading, this, user);
        RadioButtonGroup<String> answer = new RadioButtonGroup<>();
        answer.setItems(answersService.getListAnswers(exerciseReading.getAnswers()));
        Label rightAnswer = new Label("Правильна відповідь - "+exerciseReading.getRightAnswer());
        //create answer button
        Button answerButton = new Button("Відповісти", event->{
            boolean isCorrect =exerciseReading.getRightAnswer().equals(answer.getValue().trim());
            AnswerReading answerReading = new AnswerReading(answer.getValue(), exerciseReading, user, isCorrect);
            answerReadingService.save(answerReading);
            exerciseReading.addAnswerReading(answerReading);
            readingExercisesService.save(exerciseReading);
            answersService.changeColorIfAnsweredExerciseReading(true,exerciseReading, this, user);
            this.removeAll();
            this.add(text, rightAnswer);
        });
        //if the question is answered we don`t need answers anymore
        if (!answered) this.add(answer, answerButton)
                ;
        else this.add(rightAnswer)
                ;
    }
}
