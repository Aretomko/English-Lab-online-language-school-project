package com.example.application.views.admin.exercises.listeningExercise;

import com.example.application.domain.ExerciseListening;
import com.example.application.domain.User;
import com.example.application.service.ListeningExerciseService;
import com.example.application.service.ListeningService;
import com.example.application.views.admin.exercises.readingExercises.CreateReadingExerciseComponent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;

public class CreateListeningExerciseComponent extends HorizontalLayout {
    public CreateListeningExerciseComponent(ListeningExerciseService listeningExerciseService,
                                            ListeningService listeningService,
                                            Grid<ExerciseListening> grid){
        Label editLabel = new Label("Create new listening exercise");
        editLabel.setHeight("wrap-content");
        editLabel.getStyle().set("font","400 13.3333px Arial");
        editLabel.getStyle().set("font-size", "var(--lumo-font-size-s)");
        editLabel.getStyle().set("font-weight", "500");
        //create form field
        TextField question = new TextField("question");
        TextField answers = new TextField("answers sep by /");
        TextField rightAnswer = new TextField("right answer");
        TextField listeningId = new TextField("listening task id");
        //create button
        Button submit = new Button("Create" , event -> {
            try {
                listeningExerciseService.save(new ExerciseListening(question.getValue(), answers.getValue(), rightAnswer.getValue(),listeningService.findListeningById(listeningId.getValue())));
            }catch (RuntimeException ex){
                this.add(new Label("Wrong id provided"));
            }
            grid.setItems(listeningExerciseService.getAllListeningExercises());
            question.setValue("");
            answers.setValue("");
            listeningId.setValue("");
            rightAnswer.setValue("");
        });
        this.add(editLabel ,question,answers,rightAnswer,listeningId, submit);
        this.setWidth("100%");
        this.setPadding(true);
        this.setAlignItems(Alignment.BASELINE);
    }
}