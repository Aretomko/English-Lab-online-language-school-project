package com.example.application.views.admin.exercises.listeningExercise;

import com.example.application.domain.ExerciseListening;
import com.example.application.service.ListeningExerciseService;
import com.example.application.service.ListeningService;
import com.example.application.views.admin.NavbarAdmin;
import com.example.application.views.admin.exercises.listeningTasks.EditListeningTaskComponent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;

public class EditListeningExerciseComponent extends HorizontalLayout {
    public EditListeningExerciseComponent(ExerciseListening exerciseListening,
                                          ListeningService listeningService,
                                          ListeningExerciseService listeningExerciseService,
                                          Grid<ExerciseListening> grid,
                                          ListeningExercisesAdminView listeningExercisesAdminView,
                                          NavbarAdmin navbarAdmin,
                                          CreateListeningExerciseComponent createListeningExerciseComponent){
        Label editLabel = new Label("Edit listening exercise " + exerciseListening.getId());
        editLabel.setHeight("wrap-content");
        editLabel.getStyle().set("font","400 13.3333px Arial");
        editLabel.getStyle().set("font-size", "var(--lumo-font-size-s)");
        editLabel.getStyle().set("font-weight", "500");
        //create form field
        TextField question = new TextField("question");
        TextField answers = new TextField("answers sep by /");
        TextField rightAnswer = new TextField("right answer");
        TextField listeningId = new TextField("listening task id");
        if(exerciseListening.getText()!=null) question.setValue(exerciseListening.getText())
                ;
        else question.setPlaceholder("not set")
                ;
        if(exerciseListening.getAnswers()!=null) answers.setValue(exerciseListening.getAnswers())
                ;
        else answers.setPlaceholder("not set")
                ;
        if (exerciseListening.getRightAnswer()!=null) rightAnswer.setValue(exerciseListening.getRightAnswer())
                ;
        else rightAnswer.setPlaceholder("not set")
                ;
        if(exerciseListening.getListening()!=null) listeningId.setValue(String.valueOf(exerciseListening.getListening().getId()))
                ;
        else listeningId.setPlaceholder("not set")
                ;
        //create submitting button
        Button submit = new Button("Update" , event -> {
            exerciseListening.setText(question.getValue());
            exerciseListening.setAnswers(answers.getValue());
            exerciseListening.setRightAnswer(rightAnswer.getValue());
            exerciseListening.setListening(listeningService.findListeningById(listeningId.getValue()));
            listeningExerciseService.save(exerciseListening);
            grid.getDataProvider().refreshItem(exerciseListening);
            listeningExercisesAdminView.removeAll();
            listeningExercisesAdminView.add(navbarAdmin, createListeningExerciseComponent, grid);
        });
        this.add(editLabel ,question,answers, rightAnswer,listeningId, submit);
        this.setWidth("100%");
        this.setPadding(true);
        this.setAlignItems(Alignment.BASELINE);
    }
}
