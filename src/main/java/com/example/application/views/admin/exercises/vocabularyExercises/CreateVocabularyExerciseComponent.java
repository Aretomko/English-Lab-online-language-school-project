package com.example.application.views.admin.exercises.vocabularyExercises;

import com.example.application.domain.ExerciseVocabulary;
import com.example.application.domain.User;
import com.example.application.service.LessonsService;
import com.example.application.service.VocabularyExerciseService;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;

public class    CreateVocabularyExerciseComponent extends HorizontalLayout {
    private final VocabularyExerciseService vocabularyExerciseService;
    private final LessonsService lessonsService;
    private Grid<ExerciseVocabulary> grid;
    //UI componetnts
    private Label createLabel;
    private TextField question;
    private TextField answers;
    private TextField lessonId;
    private TextField rightAnswer;
    private Checkbox isHomework;
    private Button submit;

    public CreateVocabularyExerciseComponent(VocabularyExerciseService vocabularyExerciseService,
                                             LessonsService lessonsService,
                                             Grid<ExerciseVocabulary> grid){
        this.vocabularyExerciseService = vocabularyExerciseService;
        this.lessonsService = lessonsService;
        this.grid = grid;
        //UI initialization
        this.createLabel = new Label("Create new vocabulary exercise");
        createLabel.setHeight("wrap-content");
        createLabel.getStyle().set("font","400 13.3333px Arial");
        createLabel.getStyle().set("font-size", "var(--lumo-font-size-s)");
        createLabel.getStyle().set("font-weight", "500");
        //create form field
        question = new TextField("question");
        answers = new TextField("answers sep by /");
        lessonId = new TextField("lesson id");
        rightAnswer =new TextField("Right answer");
        isHomework =new Checkbox("is homework");
        submit = new Button("Create" , event -> {
            this.createVocabularyExercise();
        });
        this.add(createLabel , question,rightAnswer, lessonId, isHomework, submit);
        this.setWidth("100%");
        this.setPadding(true);
        this.setAlignItems(Alignment.BASELINE);
    }
    public void createVocabularyExercise(){
        try {
            vocabularyExerciseService.save(new ExerciseVocabulary(question.getValue(),
                    answers.getValue(),
                    rightAnswer.getValue(),
                    lessonsService.findLessonById(lessonId.getValue()),
                    isHomework.getValue()));
        }catch (RuntimeException ex){
            Notification.show("Wrong id provided! Id should be just an integer");
        }
        grid.setItems(vocabularyExerciseService.getAllVocabularyExercises());
        question.setValue("");
        answers.setValue("");
        lessonId.setValue("");
        isHomework.setValue(false);
    }

    public Label getCreateLabel() {
        return createLabel;
    }

    public TextField getQuestion() {
        return question;
    }

    public TextField getAnswers() {
        return answers;
    }

    public TextField getLessonId() {
        return lessonId;
    }

    public TextField getRightAnswer() {
        return rightAnswer;
    }

    public Checkbox getIsHomework() {
        return isHomework;
    }

    public Button getSubmit() {
        return submit;
    }
}
