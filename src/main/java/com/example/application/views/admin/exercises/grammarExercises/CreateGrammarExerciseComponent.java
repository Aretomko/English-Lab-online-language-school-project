package com.example.application.views.admin.exercises.grammarExercises;

import com.example.application.domain.ExerciseGrammar;
import com.example.application.service.GrammarExerciseService;
import com.example.application.service.LessonsService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;

public class CreateGrammarExerciseComponent extends HorizontalLayout {
    //dependencies
    private final GrammarExerciseService grammarExerciseService;
    private final LessonsService lessonsService;
    private final Grid<ExerciseGrammar> grid;
    //UI components
    private Label editLabel;
    private TextField textStart;
    private TextField answers;
    private TextField rightAnswer;
    private TextField textEnd;
    private TextField lessonId;
    private Checkbox isHomework;
    private Button submit;

    public CreateGrammarExerciseComponent(GrammarExerciseService grammarExerciseService,
                                          LessonsService lessonsService,
                                          Grid<ExerciseGrammar> grid){
        //initialize dependencies
        this.grammarExerciseService = grammarExerciseService;
        this.lessonsService = lessonsService;
        this.grid = grid;
        //create UI
        this.editLabel = new Label("Create new user");
        editLabel.setHeight("wrap-content");
        editLabel.getStyle().set("font","400 13.3333px Arial");
        editLabel.getStyle().set("font-size", "var(--lumo-font-size-s)");
        editLabel.getStyle().set("font-weight", "500");
        //create form field
        this.textStart = new TextField("start of the text");
        this.answers = new TextField("answers sep by / ");
        this.rightAnswer = new TextField("right answer");
        this.textEnd = new TextField("end of the text");
        this.lessonId = new TextField("lesson id");
        this.isHomework = new Checkbox("is homework");
        this.submit = new Button("Create" , event -> {
            this.createGrammarEx();
        });
        this.add(editLabel ,textStart, answers, rightAnswer, textEnd, lessonId,isHomework, submit);
        this.setWidth("100%");
        this.setPadding(true);
        this.setAlignItems(Alignment.BASELINE);
    }

    public void createGrammarEx(){
        try {
            grammarExerciseService.save(new ExerciseGrammar(textStart.getValue(),
                    textEnd.getValue(),
                    answers.getValue(),
                    rightAnswer.getValue(),
                    lessonsService.findLessonById(lessonId.getValue()),
                    isHomework.getValue()));
        }catch (RuntimeException ex){
            Notification.show("Wrong id provided! Id should be just an integer number");
        }
        grid.setItems(grammarExerciseService.getAllGrammarExercises());
        textStart.setValue("");
        textEnd.setValue("");
        answers.setValue("");
        lessonId.setValue("");
        isHomework.setValue(false);
    }

    public Label getEditLabel() {
        return editLabel;
    }

    public TextField getTextStart() {
        return textStart;
    }

    public TextField getAnswers() {
        return answers;
    }

    public TextField getRightAnswer() {
        return rightAnswer;
    }

    public TextField getTextEnd() {
        return textEnd;
    }

    public TextField getLessonId() {
        return lessonId;
    }

    public Checkbox getIsHomework() {
        return isHomework;
    }

    public Button getSubmit() {
        return submit;
    }
}
