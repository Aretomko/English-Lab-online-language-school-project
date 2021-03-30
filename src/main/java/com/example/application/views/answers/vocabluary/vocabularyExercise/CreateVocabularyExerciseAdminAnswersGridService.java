package com.example.application.views.answers.vocabluary.vocabularyExercise;

import com.example.application.domain.ExerciseVocabulary;
import com.example.application.domain.Lesson;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.stereotype.Service;

@Service
public class CreateVocabularyExerciseAdminAnswersGridService {
    public Grid<ExerciseVocabulary> createExerciseVocabularyGridByLesson(Lesson lesson){
        Grid<ExerciseVocabulary> grid = new Grid<>();
        grid.setItems(lesson.getExercisesVocabulary());
        grid.addColumn(ExerciseVocabulary::getAnswer).setHeader("Possible answers");
        grid.addColumn(ExerciseVocabulary::getRightAnswer).setHeader("Right answer");
        grid.addComponentColumn(this::createSeeTeamAnswersExercisesVocabulary).setHeader("See answers");
        return grid;
    }
    private Button createSeeTeamAnswersExercisesVocabulary(ExerciseVocabulary exerciseVocabulary){
        Button button = new Button("See answers", event-> {
            VaadinSession.getCurrent().setAttribute("vocabularyExerciseId", exerciseVocabulary.getId());
            UI.getCurrent().navigate("admin/answers/answers/vocabulary");
        });
        return button;
    }


}
