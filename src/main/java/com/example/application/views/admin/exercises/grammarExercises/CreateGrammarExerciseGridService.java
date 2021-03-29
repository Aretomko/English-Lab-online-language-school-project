package com.example.application.views.admin.exercises.grammarExercises;

import com.example.application.domain.AnswerGrammar;
import com.example.application.domain.ExerciseGrammar;
import com.example.application.service.AnswerGrammarService;
import com.example.application.service.GrammarExerciseService;
import com.example.application.service.LessonsService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.data.provider.ListDataProvider;
import org.springframework.stereotype.Service;

@Service
public class CreateGrammarExerciseGridService {
    private final GrammarExerciseService grammarExerciseService;
    private final LessonsService lessonsService;
    private final AnswerGrammarService answerGrammarService;

    public CreateGrammarExerciseGridService(GrammarExerciseService grammarExerciseService, LessonsService lessonsService, AnswerGrammarService answerGrammarService) {
        this.grammarExerciseService = grammarExerciseService;
        this.lessonsService = lessonsService;
        this.answerGrammarService = answerGrammarService;
    }

    public Grid<ExerciseGrammar> crateGridExerciseGrammar(){
        Grid<ExerciseGrammar> grid = new Grid<>();
        grid.setItems(grammarExerciseService.getAllGrammarExercises());
        grid.addColumn(ExerciseGrammar::getTextStart).setHeader("Fist part");
        grid.addColumn(ExerciseGrammar::getAnswers).setHeader("Answers");
        grid.addColumn(ExerciseGrammar::getRightAnswer).setHeader("Right answer");
        grid.addColumn(ExerciseGrammar::getTextEnd).setHeader("Second part");
        grid.addColumn(ExerciseGrammar::getHomework).setHeader("Is Homework");
        grid.addColumn(item -> lessonsService.getStringNameIdByGrammarExercise(item)).setHeader("Lesson");
        grid.addComponentColumn(item -> createRemoveButtonGrammarExercise(grid, item)).setHeader("Delete exercise");
        return grid;
    }

    private Button createRemoveButtonGrammarExercise(Grid<ExerciseGrammar> grid, ExerciseGrammar exerciseGrammar){
        Button button = new Button("Delete", event->{
            //delete related answers
            for(AnswerGrammar answerGrammar: exerciseGrammar.getAnswersGrammar()){
                answerGrammarService.delete(answerGrammar);
            }
            ListDataProvider<ExerciseGrammar> dataProvider = (ListDataProvider<ExerciseGrammar>) grid.getDataProvider();
            dataProvider.getItems().remove(exerciseGrammar);
            grammarExerciseService.delete(exerciseGrammar);
            dataProvider.refreshAll();
        });
        return button;
    }
}
