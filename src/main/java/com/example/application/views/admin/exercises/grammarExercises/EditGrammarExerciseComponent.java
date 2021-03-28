package com.example.application.views.admin.exercises.grammarExercises;

import com.example.application.domain.ExerciseGrammar;
import com.example.application.service.GrammarExerciseService;
import com.example.application.service.LessonsService;
import com.example.application.views.admin.NavbarAdmin;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;

public class EditGrammarExerciseComponent extends HorizontalLayout {
    private final ExerciseGrammar exerciseGrammar;
    private final LessonsService lessonsService;
    private final GrammarExerciseService grammarExerciseService;
    private final Grid<ExerciseGrammar> grid;
    private final NavbarAdmin navbarAdmin;
    private final CreateGrammarExerciseComponent createGrammarExerciseComponent;
    private final GrammarExercisesAdminView grammarExercisesAdminView;
    //UI components
    private Label editLabel;
    private TextField textStart;
    private TextField answers;
    private TextField rightAnswer;
    private TextField textEnd;
    private TextField lessonId;
    private Checkbox isHomework;
    private Button submit;

    public EditGrammarExerciseComponent(ExerciseGrammar exerciseGrammar,
                                        LessonsService lessonsService,
                                        GrammarExerciseService grammarExerciseService,
                                        Grid<ExerciseGrammar> grid,
                                        NavbarAdmin navbarAdmin,
                                        CreateGrammarExerciseComponent createGrammarExerciseComponent,
                                        GrammarExercisesAdminView grammarExercisesAdminView){
        //initialize dependencies
        this.exerciseGrammar =exerciseGrammar;
        this.lessonsService = lessonsService;
        this.grammarExerciseService = grammarExerciseService;
        this.grid = grid;
        this.navbarAdmin = navbarAdmin;
        this.createGrammarExerciseComponent = createGrammarExerciseComponent;
        this.grammarExercisesAdminView = grammarExercisesAdminView;
        //initialize UI components
        this.editLabel = new Label("Edit grammar exercise " + exerciseGrammar.getId());
        editLabel.setHeight("wrap-content");
        editLabel.getStyle().set("font","400 13.3333px Arial");
        editLabel.getStyle().set("font-size", "var(--lumo-font-size-s)");
        editLabel.getStyle().set("font-weight", "500");
        //create form field
        textStart = new TextField("start of the text");
        answers = new TextField("answers sep by /");
        rightAnswer = new TextField("right answer");
        textEnd = new TextField("end of the text");
        lessonId = new TextField("lesson id");
        if(exerciseGrammar.getTextStart()!=null) textStart.setValue(exerciseGrammar.getTextStart())
                ;
        else textStart.setPlaceholder("net set")
                ;
        if (exerciseGrammar.getTextEnd()!=null) textEnd.setValue(exerciseGrammar.getTextEnd())
                ;
        else textEnd.setPlaceholder("not set")
                ;
        if (exerciseGrammar.getAnswers()!=null) answers.setValue(exerciseGrammar.getAnswers())
                ;
        else answers.setPlaceholder("not set")
                ;
        if (exerciseGrammar.getRightAnswer()!=null) rightAnswer.setValue(exerciseGrammar.getRightAnswer())
                ;
        else rightAnswer.setPlaceholder("not set")
                ;
        if (exerciseGrammar.getLesson()!=null) lessonId.setValue(String.valueOf(exerciseGrammar.getLesson().getId()))
                ;
        else lessonId.setValue("not set")
                ;
        if (exerciseGrammar.getHomework()!=null) isHomework.setValue(exerciseGrammar.getHomework())
                ;
        else isHomework.setValue(false)
                ;
        //create button
        Button submit = new Button("Update" , event -> {
            this.updateExerciseGrammar();
        });
        this.add(editLabel ,textStart, answers,rightAnswer, textEnd, lessonId,isHomework, submit);
        this.setWidth("100%");
        this.setPadding(true);
        this.setAlignItems(Alignment.BASELINE);
    }
    public void updateExerciseGrammar(){
        exerciseGrammar.setTextStart(textStart.getValue());
        exerciseGrammar.setTextEnd(textEnd.getValue());
        exerciseGrammar.setAnswers(answers.getValue());
        exerciseGrammar.setRightAnswer(rightAnswer.getValue());
        exerciseGrammar.setHomework(isHomework.getValue());
        exerciseGrammar.setLesson(lessonsService.findLessonById(lessonId.getValue()));
        grammarExerciseService.save(exerciseGrammar);
        grid.getDataProvider().refreshItem(exerciseGrammar);
        grammarExercisesAdminView.removeAll();
        grammarExercisesAdminView.add(navbarAdmin, createGrammarExerciseComponent, grid);
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
