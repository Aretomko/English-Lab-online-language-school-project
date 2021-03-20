package com.example.application.views.admin.exercises.grammarExercises;

import com.example.application.domain.ExerciseGrammar;
import com.example.application.service.GrammarExerciseService;
import com.example.application.service.LessonsService;
import com.example.application.views.admin.NavbarAdmin;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;

public class EditGrammarExerciseComponent extends HorizontalLayout {
    public EditGrammarExerciseComponent(ExerciseGrammar exerciseGrammar,
                                        LessonsService lessonsService,
                                        GrammarExerciseService grammarExerciseService,
                                        Grid<ExerciseGrammar> grid,
                                        NavbarAdmin navbarAdmin,
                                        CreateGrammarExerciseComponent createGrammarExerciseComponent,
                                        GrammarExercisesAdminView grammarExercisesAdminView){
        Label editLabel = new Label("Edit grammar exercise " + exerciseGrammar.getId());
        editLabel.setHeight("wrap-content");
        editLabel.getStyle().set("font","400 13.3333px Arial");
        editLabel.getStyle().set("font-size", "var(--lumo-font-size-s)");
        editLabel.getStyle().set("font-weight", "500");
        //create form field
        TextField textStart = new TextField("start of the text");
        TextField answers = new TextField("answers sep by /");
        TextField rightAnswer = new TextField("right answer");
        TextField textEnd = new TextField("end of the text");
        TextField lessonId = new TextField("lesson id");
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
        //create button
        Button submit = new Button("Update" , event -> {
            exerciseGrammar.setTextStart(textStart.getValue());
            exerciseGrammar.setTextEnd(textEnd.getValue());
            exerciseGrammar.setAnswers(answers.getValue());
            exerciseGrammar.setRightAnswer(rightAnswer.getValue())  ;
            exerciseGrammar.setLesson(lessonsService.findLessonById(lessonId.getValue()));
            grammarExerciseService.save(exerciseGrammar);
            grid.getDataProvider().refreshItem(exerciseGrammar);
            grammarExercisesAdminView.removeAll();
            grammarExercisesAdminView.add(navbarAdmin, createGrammarExerciseComponent, grid);
        });
        this.add(editLabel ,textStart, answers,rightAnswer, textEnd, lessonId, submit);
        this.setWidth("100%");
        this.setPadding(true);
        this.setAlignItems(Alignment.BASELINE);
    }
}
