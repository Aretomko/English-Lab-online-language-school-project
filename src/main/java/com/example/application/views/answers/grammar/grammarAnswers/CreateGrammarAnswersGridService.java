package com.example.application.views.answers.grammar.grammarAnswers;

import com.example.application.domain.Answer;
import com.example.application.domain.Exercise;
import com.example.application.domain.ExerciseGrammar;
import com.example.application.domain.User;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CreateGrammarAnswersGridService {
    public Grid<User> createUsersGridWithGrammarAnswers(List<User> users,
                                                        ExerciseGrammar ex){
        Grid<User> grid = new Grid<>();
        grid.setItems(users);
        grid.addColumn(User::getRealN).setHeader("Name");
        grid.addColumn(User::getSurname).setHeader("Surname");
        grid.addComponentColumn(item -> new Label(ex.getRightAnswer())).setHeader("Right answer");
        grid.addComponentColumn(item -> new Label(ex.getAnswers())).setHeader("Possible answers");
        grid.addComponentColumn(item -> this.getGrammarExerciseAnswer(item,ex)).setHeader("Answer");
        return grid;
    }

    private Component getGrammarExerciseAnswer(User user,
                                               ExerciseGrammar exerciseGrammar){
        List<Answer> answers = user.getAnswersGrammar()
                .stream()
                .filter(i->i.getExerciseGrammar().equals(exerciseGrammar))
                .collect(Collectors.toList());
        return receiveOutputAnswers(answers,exerciseGrammar);
    }

    private Component receiveOutputAnswers(List<Answer> answers,
                                           Exercise exercise){
        if (answers.size()>1) return new Label("Internal error")
                ;
        if (answers.size()==0) return new Label("Not answered yet");
        if (answers.get(0).getAnswer().equals(exercise.getRightAnswer())) {
            return new Label(answers.get(0).getAnswer() + " true");
        }else{
            return new Label(answers.get(0).getAnswer() + " false");
        }
    }
}
