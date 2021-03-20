package com.example.application.views.admin.exercises.vocabularyExercises;

import com.example.application.domain.ExerciseVocabulary;
import com.example.application.domain.User;
import com.example.application.service.LessonsService;
import com.example.application.service.VocabularyExerciseService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;

public class CreateVocabularyExerciseComponent extends HorizontalLayout {
    public CreateVocabularyExerciseComponent(VocabularyExerciseService vocabularyExerciseService,
                                             LessonsService lessonsService,
                                             Grid<ExerciseVocabulary> grid){
        Label editLabel = new Label("Create new vocabulary exercise");
        editLabel.setHeight("wrap-content");
        editLabel.getStyle().set("font","400 13.3333px Arial");
        editLabel.getStyle().set("font-size", "var(--lumo-font-size-s)");
        editLabel.getStyle().set("font-weight", "500");
        //create form field
        TextField question = new TextField("question");
        TextField answers = new TextField("answers sep by /");
        TextField lessonId = new TextField("lesson id");
        TextField rightAnswer =new TextField("Right answer");
        Button submit = new Button("Create" , event -> {
            vocabularyExerciseService.save(new ExerciseVocabulary(question.getValue(),answers.getValue(),rightAnswer.getValue(),lessonsService.findLessonById(lessonId.getValue())));
            grid.setItems(vocabularyExerciseService.getAllVocabularyExercises());
            question.setValue("");
            answers.setValue("");
            lessonId.setValue("");
        });
        this.add(editLabel , question, answers,rightAnswer, lessonId, submit);
        this.setWidth("100%");
        this.setPadding(true);
        this.setAlignItems(Alignment.BASELINE);
    }
}
