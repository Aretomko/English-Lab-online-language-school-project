package com.example.application.views.admin.exercises.vocabularyExercises;

import com.example.application.domain.ExerciseVocabulary;
import com.example.application.service.LessonsService;
import com.example.application.service.VocabularyExerciseService;
import com.example.application.views.admin.NavbarAdmin;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;

public class EditVocabularyExerciseComponent extends HorizontalLayout {
    private Label createLabel;
    private TextField question;
    private TextField answers;
    private TextField lessonId;
    private TextField rightAnswer;
    private Checkbox isHomework;
    private Button submit;
    public EditVocabularyExerciseComponent(ExerciseVocabulary exerciseVocabulary,
                                           LessonsService lessonsService,
                                           VocabularyExerciseService vocabularyExerciseService,
                                           Grid<ExerciseVocabulary> grid,
                                           VocabularyExerciseAdminAdmin vocabularyExerciseAdminAdmin,
                                           NavbarAdmin navbarAdmin,
                                           CreateVocabularyExerciseComponent createVocabularyExerciseComponent){
        this.createLabel = new Label("Edit vocabulary exercise " +  exerciseVocabulary.getId());
        createLabel.setHeight("wrap-content");
        createLabel.getStyle().set("font","400 13.3333px Arial");
        createLabel.getStyle().set("font-size", "var(--lumo-font-size-s)");
        createLabel.getStyle().set("font-weight", "500");
        //create form field
        question = new TextField("question");
        answers = new TextField("answers sep by /");
        rightAnswer = new TextField("answers sep by /");
        lessonId = new TextField("lesson id");
        isHomework = new Checkbox("is homework");

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
        if (exerciseVocabulary.getHomework()!=null) isHomework.setValue(exerciseVocabulary.getHomework())
                ;
        else isHomework.setValue(false)
                ;
        //create button
        submit = new Button("Update" , event -> {
            exerciseVocabulary.setText(question.getValue());
            exerciseVocabulary.setAnswer(answers.getValue());
            exerciseVocabulary.setRightAnswer(rightAnswer.getValue());
            exerciseVocabulary.setLesson(lessonsService.findLessonById(lessonId.getValue()));
            exerciseVocabulary.setHomework(isHomework.getValue());
            vocabularyExerciseService.save(exerciseVocabulary);
            grid.getDataProvider().refreshItem(exerciseVocabulary);
            vocabularyExerciseAdminAdmin.removeAll();
            vocabularyExerciseAdminAdmin.add(navbarAdmin, createVocabularyExerciseComponent, grid);
        });
        this.add(createLabel , question,rightAnswer,lessonId, isHomework, submit);
        this.setWidth("100%");
        this.setPadding(true);
        this.setAlignItems(Alignment.BASELINE);
    }
}
