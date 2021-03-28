package com.example.application.views.user.grammarOverview;

import com.example.application.domain.AnswerGrammar;
import com.example.application.domain.ExerciseGrammar;
import com.example.application.domain.User;
import com.example.application.service.AnswerGrammarService;
import com.example.application.service.AnswersService;
import com.example.application.service.GrammarExerciseService;
import com.example.application.service.UserService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.server.VaadinSession;

public class GrammarExerciseComponent extends HorizontalLayout {
    public GrammarExerciseComponent(ExerciseGrammar exerciseGrammar,
                                    AnswersService answersService,
                                    AnswerGrammarService answerGrammarService,
                                    GrammarExerciseService grammarExerciseService,
                                    UserService userService){
        this.getStyle().set("box-shadow", "0 4px 10px 0 rgba(0,0,0,0.2), 0 4px 20px 0 rgba(0,0,0,0.19)");
        this.setWidth("100%");
        this.setPadding(true);
        String username = VaadinSession.getCurrent().getAttribute("username").toString();
        User user = userService.getUserByUsername(username);
        //change color if answered
        boolean answered =answersService.isAnsweredExerciseGrammar(exerciseGrammar, user);
        Label testStart = new Label(exerciseGrammar.getTextStart());
        testStart.getStyle().set("font-size", "25px");
        Select<String> answer = new Select<>();
        answer.setItems(answersService.getListAnswers(exerciseGrammar.getAnswers()));
        answer.setPlaceholder("Відповідь");
        Label textEnd = new Label(exerciseGrammar.getTextEnd());
        textEnd.getStyle().set("font-size", "25px");
        //create layout structure
        VerticalLayout verticalLayout = new VerticalLayout();
        HorizontalLayout horizontalLayout = new HorizontalLayout();
        horizontalLayout.setWidth("100%");
        verticalLayout.setWidth("100%");
        Button answerButton = new Button("Відповісти", event->{
               boolean isCorrect =exerciseGrammar.getRightAnswer().equals(answer.getValue().trim());
               AnswerGrammar answerGrammar = new AnswerGrammar(answer.getValue(), exerciseGrammar, user, isCorrect);
               answerGrammarService.save(answerGrammar);
               exerciseGrammar.addAnswer(answerGrammar);
               grammarExerciseService.save(exerciseGrammar);
               answersService.changeColorIfAnsweredExerciseGrammar(true, exerciseGrammar, this,user);
               this.removeAll();
               verticalLayout.removeAll();
               horizontalLayout.add(testStart, textEnd);
               verticalLayout.add(horizontalLayout);
               verticalLayout.add(new Label("Правильна відповідь - "+ exerciseGrammar.getRightAnswer()));
               this.add(verticalLayout);
        });
        //pace button under question string
        if (answered){
            Label rightAnswer =  new Label(exerciseGrammar.getRightAnswer());
            rightAnswer.getStyle().set("font-size", "25px");
            horizontalLayout.add(testStart, rightAnswer, textEnd);
            this.add(horizontalLayout);
            answersService.changeColorIfAnsweredExerciseGrammar(answered,exerciseGrammar,this, user);
        }else {
            horizontalLayout.add(testStart, answer, textEnd);
            verticalLayout.add(horizontalLayout, answerButton);
            this.add(verticalLayout);
        }
    }
}
