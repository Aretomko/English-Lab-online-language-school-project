package com.example.application.views.answers.vocabluary.vocabularyAnswers;

import com.example.application.domain.Answer;
import com.example.application.domain.Exercise;
import com.example.application.domain.ExerciseVocabulary;
import com.example.application.domain.User;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CreateVocabularyAnswersAdminAnswersGridService {
    public Grid<User> createUserGridWithVocabularyAnswers(List<User> users,
                                                          ExerciseVocabulary exerciseVocabulary){
        Grid<User> grid = new Grid<>();
        grid.setItems(users);
        grid.addColumn(User::getRealN).setHeader("Name");
        grid.addColumn(User::getSurname).setHeader("Surname");
        grid.addComponentColumn(item -> new Label(exerciseVocabulary.getAnswer()));
        grid.addComponentColumn(item -> new Label(exerciseVocabulary.getRightAnswer()));
        grid.addComponentColumn(item-> this.getVocabularyExerciseAnswer(item,exerciseVocabulary));
        return grid;
    }
    public Component getVocabularyExerciseAnswer(User user,
                                                 ExerciseVocabulary exerciseVocabulary){
        List<Answer> answers = user.getAnswersVocabulary()
                .stream()
                .filter(i-> !i.getExerciseVocabulary().equals(exerciseVocabulary))
                .collect(Collectors.toList());
        return receiveOutputAnswers(answers,exerciseVocabulary);
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
