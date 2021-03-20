package com.example.application.views.admin.exercises.readingExercises;

import com.example.application.domain.ExerciseReading;
import com.example.application.service.ReadingExercisesService;
import com.example.application.service.ReadingService;
import com.example.application.views.admin.NavbarAdmin;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;

public class EditReadingExerciseComponent  extends HorizontalLayout {
    public EditReadingExerciseComponent(ExerciseReading exerciseReading,
                                        ReadingExercisesService readingExercisesService,
                                        ReadingService readingService,
                                        Grid<ExerciseReading> grid,
                                        ReadingExercisesAdminView readingExercisesAdminView,
                                        NavbarAdmin navbarAdmin,
                                        CreateReadingExerciseComponent createReadingExerciseComponent){
        Label editLabel = new Label("Edit reading exercise " +exerciseReading.getId());
        editLabel.setHeight("wrap-content");
        editLabel.getStyle().set("font","400 13.3333px Arial");
        editLabel.getStyle().set("font-size", "var(--lumo-font-size-s)");
        editLabel.getStyle().set("font-weight", "500");
        //create form field
        TextField question = new TextField("question");
        TextField answers = new TextField("answers sep by /");
        TextField rightAnswer = new TextField("right answer");
        TextField readingId = new TextField("reading task id");
        if (exerciseReading.getText()!=null) question.setValue(exerciseReading.getText())
                ;
        else question.setPlaceholder("not set")
                ;
        if (exerciseReading.getAnswers()!=null) answers.setValue(exerciseReading.getAnswers())
                ;
        else answers.setPlaceholder("not set")
                ;
        if (exerciseReading.getRightAnswer()!=null) rightAnswer.setValue(exerciseReading.getRightAnswer())
                ;
        else rightAnswer.setPlaceholder("not set")
                ;
        if (exerciseReading.getReading()!=null) readingId.setValue(String.valueOf(exerciseReading.getReading().getId()))
                ;
        else readingId.setPlaceholder("not set")
                ;
        //create submitting button
        Button submit = new Button("Update" , event -> {
            exerciseReading.setText(question.getValue());
            exerciseReading.setAnswers(answers.getValue());
            exerciseReading.setRightAnswer(rightAnswer.getValue());
            exerciseReading.setReading(readingService.findReadingById(readingId.getValue()));
            readingExercisesService.save(exerciseReading);
            grid.getDataProvider().refreshItem(exerciseReading);
            readingExercisesAdminView.removeAll();
            readingExercisesAdminView.add(navbarAdmin, createReadingExerciseComponent, grid);
        });
        this.add(editLabel ,question,answers, rightAnswer,readingId, submit);
        this.setWidth("100%");
        this.setPadding(true);
        this.setAlignItems(Alignment.BASELINE);
    }
}
