package com.example.application.views.admin.exercises.readingExercises;

import com.example.application.domain.ExerciseReading;
import com.example.application.domain.User;
import com.example.application.service.ReadingExercisesService;
import com.example.application.service.ReadingService;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;

public class CreateReadingExerciseComponent extends HorizontalLayout {
    public CreateReadingExerciseComponent(ReadingExercisesService readingExercisesService,
                                          ReadingService readingService,
                                          Grid<ExerciseReading> grid){
        Label editLabel = new Label("Create new reading exercise");
        editLabel.setHeight("wrap-content");
        editLabel.getStyle().set("font","400 13.3333px Arial");
        editLabel.getStyle().set("font-size", "var(--lumo-font-size-s)");
        editLabel.getStyle().set("font-weight", "500");
        //create form field
        TextField question = new TextField("question");
        TextField answers = new TextField("answers sep by /");
        TextField rightAnswer = new TextField("right answer");
        TextField readingId = new TextField("reading task id");
        //create button
        Button submit = new Button("Create" , event -> {
            try {
                readingExercisesService.save(new ExerciseReading(question.getValue(), answers.getValue(),rightAnswer.getValue(), readingService.findReadingById(readingId.getValue())));
            }catch (RuntimeException ex){
                this.add(new Label("Wrong id provided! Id should be just an integer"));
            }
            grid.setItems(readingExercisesService.getAllReadingExercises());
            question.setValue("");
            answers.setValue("");
            readingId.setValue("");
            rightAnswer.setValue("");
        });
        this.add(editLabel , question , answers,rightAnswer, readingId, submit);
        this.setWidth("100%");
        this.setPadding(true);
        this.setAlignItems(Alignment.BASELINE);
    }
}
