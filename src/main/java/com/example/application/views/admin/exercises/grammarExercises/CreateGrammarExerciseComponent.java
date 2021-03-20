package com.example.application.views.admin.exercises.grammarExercises;

import com.example.application.domain.ExerciseGrammar;
import com.example.application.service.GrammarExerciseService;
import com.example.application.service.LessonsService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;

public class CreateGrammarExerciseComponent extends HorizontalLayout {
    public CreateGrammarExerciseComponent(GrammarExerciseService grammarExerciseService,
                                          LessonsService lessonsService,
                                          Grid<ExerciseGrammar> grid){
        Label editLabel = new Label("Create new user");
        editLabel.setHeight("wrap-content");
        editLabel.getStyle().set("font","400 13.3333px Arial");
        editLabel.getStyle().set("font-size", "var(--lumo-font-size-s)");
        editLabel.getStyle().set("font-weight", "500");
        //create form field
        TextField textStart = new TextField("start of the text");
        TextField answers = new TextField("answers sep by | ");
        TextField rightAnswer = new TextField("right answer");
        TextField textEnd = new TextField("end of the text");
        TextField lessonId = new TextField("lesson id");
        //create button
        Button submit = new Button("Create" , event -> {
            grammarExerciseService.save(new ExerciseGrammar(textStart.getValue(),
                    textEnd.getValue(),
                    answers.getValue(),
                    rightAnswer.getValue(),
                    lessonsService.findLessonById(lessonId.getValue())));
            grid.setItems(grammarExerciseService.getAllGrammarExercises());
            textStart.setValue("");
            textEnd.setValue("");
            answers.setValue("");
            lessonId.setValue("");
        });
        this.add(editLabel ,textStart, answers, rightAnswer, textEnd, lessonId, submit);
        this.setWidth("100%");
        this.setPadding(true);
        this.setAlignItems(Alignment.BASELINE);
    }
}
