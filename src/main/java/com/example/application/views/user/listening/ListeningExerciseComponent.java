package com.example.application.views.user.listening;

import com.example.application.domain.AnswerListening;
import com.example.application.domain.ExerciseListening;
import com.example.application.domain.User;
import com.example.application.service.AnswersListeningService;
import com.example.application.service.AnswersService;
import com.example.application.service.ListeningExerciseService;
import com.example.application.service.UserService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.radiobutton.RadioButtonGroup;
import com.vaadin.flow.server.VaadinSession;

public class ListeningExerciseComponent extends VerticalLayout {
    public ListeningExerciseComponent(ExerciseListening exerciseListening,
                                      AnswersService answersService,
                                      UserService userService,
                                      AnswersListeningService answersListeningService,
                                      ListeningExerciseService listeningExerciseService){
        this.getStyle().set("box-shadow", "0 4px 10px 0 rgba(0,0,0,0.2), 0 4px 20px 0 rgba(0,0,0,0.19)");
        this.setWidth("100%");
        this.setPadding(true);
        //display question
        Label text = new Label(exerciseListening.getText());
        this.add(text);
        String username = VaadinSession.getCurrent().getAttribute("username").toString();
        User user = userService.getUserByUsername(username);
        boolean answered =answersService.isAnsweredExerciseListening(exerciseListening, user);
        answersService.changeColorIfAnsweredExerciseListening(answered, exerciseListening, this, user);
        //display possible answers
        RadioButtonGroup<String> answer = new RadioButtonGroup<>();
        answer.setItems(answersService.getListAnswers(exerciseListening.getAnswers()));
        //create answer button
        Button answerButton = new Button("Відповісти", event->{
            //process answer
            boolean isCorrect = exerciseListening.getRightAnswer().equals(answer.getValue());
            AnswerListening answerListening = new AnswerListening(answer.getValue(), exerciseListening, user, isCorrect);
            answersListeningService.save(answerListening);
            exerciseListening.addAnswer(answerListening);
            listeningExerciseService.save(exerciseListening);
            answersService.changeColorIfAnsweredExerciseListening(true,exerciseListening,this,user);
            this.removeAll();
            this.add(text);
            this.add(new Label("Правильна відповідь - "+exerciseListening.getRightAnswer()));
        });
        if(answered){
            this.add(new Label("Правильна відповідь - "+exerciseListening.getRightAnswer()));
        }else{
            this.add(answer, answerButton);
        }
    }
}
