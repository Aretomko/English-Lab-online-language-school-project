package com.example.application.views.admin.exercises.vocabularyExercises;

import com.example.application.domain.ExerciseVocabulary;
import com.example.application.service.LessonsService;
import com.example.application.service.VocabularyExerciseService;
import com.example.application.views.admin.NavbarAdmin;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;

public class EditVocabularyExerciseComponent extends HorizontalLayout {
    public EditVocabularyExerciseComponent(ExerciseVocabulary exerciseVocabulary,
                                           LessonsService lessonsService,
                                           VocabularyExerciseService vocabularyExerciseService,
                                           Grid<ExerciseVocabulary> grid,
                                           VocabularyExerciseAdminAdmin vocabularyExerciseAdminAdmin,
                                           NavbarAdmin navbarAdmin,
                                           CreateVocabularyExerciseComponent createVocabularyExerciseComponent){
        Label editLabel = new Label("Edit vocabulary exercise " +  exerciseVocabulary.getId());
        editLabel.setHeight("wrap-content");
        editLabel.getStyle().set("font","400 13.3333px Arial");
        editLabel.getStyle().set("font-size", "var(--lumo-font-size-s)");
        editLabel.getStyle().set("font-weight", "500");
        //create form field
        TextField question = new TextField("question");
        TextField answers = new TextField("answers sep by /");
        TextField rightAnswer = new TextField("answers sep by /");
        TextField lessonId = new TextField("lesson id");
        if(exerciseVocabulary.getText()!=null) question.setValue(exerciseVocabulary.getText())
                ;
        else question.setPlaceholder("not set")
                ;
        if (exerciseVocabulary.getAnswer()!=null) answers.setValue(exerciseVocabulary.getAnswer())
                ;
        else answers.setPlaceholder("not set")
                ;
        if (exerciseVocabulary.getRightAnswer()!=null) rightAnswer.setValue(exerciseVocabulary.getRightAnswer())
                ;
        else rightAnswer.setPlaceholder("not set")
                ;
        if (exerciseVocabulary.getLesson()!=null) lessonId.setValue(String.valueOf(exerciseVocabulary.getLesson().getId()))
                ;
        else lessonId.setPlaceholder("not set")
                ;
        //create button
        Button submit = new Button("Update" , event -> {
            exerciseVocabulary.setText(question.getValue());
            exerciseVocabulary.setAnswer(answers.getValue());
            exerciseVocabulary.setRightAnswer(rightAnswer.getValue());
            exerciseVocabulary.setLesson(lessonsService.findLessonById(lessonId.getValue()));
            vocabularyExerciseService.save(exerciseVocabulary);
            grid.getDataProvider().refreshItem(exerciseVocabulary);
            vocabularyExerciseAdminAdmin.removeAll();
            vocabularyExerciseAdminAdmin.add(navbarAdmin, createVocabularyExerciseComponent, grid);
        });
        this.add(editLabel , question,answers,rightAnswer,lessonId, submit);
        this.setWidth("100%");
        this.setPadding(true);
        this.setAlignItems(Alignment.BASELINE);
    }
}
