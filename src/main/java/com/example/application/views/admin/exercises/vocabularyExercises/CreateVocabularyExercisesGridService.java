package com.example.application.views.admin.exercises.vocabularyExercises;

import com.example.application.domain.AnswerVocabulary;
import com.example.application.domain.ExerciseVocabulary;
import com.example.application.service.AnswerVocabularyService;
import com.example.application.service.LessonsService;
import com.example.application.service.VocabularyExerciseService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.data.provider.ListDataProvider;
import org.springframework.stereotype.Service;

@Service
public class CreateVocabularyExercisesGridService {
    private final VocabularyExerciseService vocabularyExerciseService;
    private final LessonsService lessonsService;
    private final AnswerVocabularyService answerVocabularyService;

    private Grid<ExerciseVocabulary> grid;

    public CreateVocabularyExercisesGridService(VocabularyExerciseService vocabularyExerciseService,
                                                LessonsService lessonsService,
                                                AnswerVocabularyService answerVocabularyService) {
        this.vocabularyExerciseService = vocabularyExerciseService;
        this.lessonsService = lessonsService;
        this.answerVocabularyService = answerVocabularyService;
    }

    public Grid<ExerciseVocabulary> createGridExerciseVocabulary(){
        this.grid = new Grid<>();
        grid.setItems(vocabularyExerciseService.getAllVocabularyExercises());
        grid.addColumn(ExerciseVocabulary::getText).setHeader("Question text");
        grid.addColumn(ExerciseVocabulary::getAnswer).setHeader("Answers sep by /");
        grid.addColumn(ExerciseVocabulary::getRightAnswer).setHeader("Right answer");
        grid.addColumn(item -> lessonsService.getStringNameIdByVocabularyExercise(item)).setHeader("Lesson");
        grid.addComponentColumn(item -> createRemoveButtonExerciseVocabulary(grid, item));
        return grid;
    }
    public Button createRemoveButtonExerciseVocabulary(Grid<ExerciseVocabulary> grid, ExerciseVocabulary exerciseVocabulary){
        Button button = new Button("Delete", event->{
            ListDataProvider<ExerciseVocabulary> dataProvider = (ListDataProvider<ExerciseVocabulary>) grid.getDataProvider();
            for(AnswerVocabulary answer:exerciseVocabulary.getAnswers()){
                answerVocabularyService.delete(answer);
            }
            dataProvider.getItems().remove(exerciseVocabulary);
            vocabularyExerciseService.delete(exerciseVocabulary);
            dataProvider.refreshAll();
        });
        return button;
    }

    public Grid<ExerciseVocabulary> getGrid() {
        return grid;
    }
}
