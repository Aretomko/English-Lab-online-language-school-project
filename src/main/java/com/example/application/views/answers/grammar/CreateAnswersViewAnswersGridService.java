package com.example.application.views.answers.grammar;

import com.example.application.domain.ExerciseGrammar;
import com.example.application.domain.Lesson;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.stereotype.Service;

@Service
public class CreateAnswersViewAnswersGridService {
    public Grid<ExerciseGrammar> createExerciseGrammarGridByLesson(Lesson lesson){
        Grid<ExerciseGrammar> grid = new Grid<>();
        grid.setItems(lesson.getExercisesGrammar());
        grid.addColumn(ExerciseGrammar::getAnswers).setHeader("Possible answers");
        grid.addColumn(ExerciseGrammar::getRightAnswer).setHeader("Right answer");
        grid.addComponentColumn(this::createSeeTeamAnswersExercisesGrammar).setHeader("See answers");
        return grid;
    }

    private Button createSeeTeamAnswersExercisesGrammar(ExerciseGrammar exerciseGrammar){
        Button button = new Button("See answers", event-> {
            VaadinSession.getCurrent().setAttribute("grammarExerciseId", exerciseGrammar.getId());
            UI.getCurrent().navigate("admin/answers/answers/grammar");
        });
        return button;
    }
}
