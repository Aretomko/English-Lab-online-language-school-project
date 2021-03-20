package com.example.application.service;

import com.example.application.domain.*;
import com.example.application.repos.AnswerVocabularyRepo;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnswersService {
    private final AnswerVocabularyRepo answerVocabularyRepo;
    private final UserService userService;

    public AnswersService(AnswerVocabularyRepo answerVocabularyRepo, UserService userService) {
        this.answerVocabularyRepo = answerVocabularyRepo;
        this.userService = userService;
    }

    public List<String> getListAnswers(String input){
        input = input.trim();
        return Arrays.asList(input.split("/"));
    }
    public void changeColorIfAnsweredExerciseVocabulary(boolean answered, ExerciseVocabulary exerciseVocabulary, VerticalLayout layout, User user){
        if (answered) {
            List<AnswerVocabulary> answers = exerciseVocabulary.getAnswers().stream().filter(i->i.getUser().equals(user)).collect(Collectors.toList());
            if (answers.size()>1|| answers.size()==0) return;
            if (answers.get(0).getCorrect()) layout.getStyle().set("background-color","#99ff99")
                    ;
            else layout.getStyle().set("background-color","#ff6666")
                    ;
        }
    }
    public void changeColorIfAnsweredExerciseGrammar(boolean answered, ExerciseGrammar exerciseGrammar, HorizontalLayout layout, User user){
        if (answered) {
            List<AnswerGrammar> answers = exerciseGrammar.getAnswersGrammar().stream().filter(i->i.getUser().equals(user)).collect(Collectors.toList());
            if (answers.size()>1|| answers.size()==0) return;
            if (answers.get(0).getCorrect()) layout.getStyle().set("background-color","#99ff99")
                    ;
            else layout.getStyle().set("background-color","#ff6666")
                    ;
        }
    }
    public void changeColorIfAnsweredExerciseReading(boolean answered, ExerciseReading exerciseReading, VerticalLayout layout, User user){
        if (answered) {
            List<AnswerReading> answers = exerciseReading.getAnswersReading().stream().filter(i->i.getUser().equals(user)).collect(Collectors.toList());
            if (answers.size()>1|| answers.size()==0) return;
            if (answers.get(0).getCorrect()) layout.getStyle().set("background-color","#99ff99")
                    ;
            else layout.getStyle().set("background-color","#ff6666")
                    ;
        }
    }
    public void changeColorIfAnsweredExerciseListening(boolean answered,ExerciseListening exerciseListening, VerticalLayout layout, User user){
        if (answered) {
            List<AnswerListening> answers = exerciseListening.getAnswersListening().stream().filter(i->i.getUser().equals(user)).collect(Collectors.toList());
            if (answers.size()==0) return;
            if (answers.get(0).getCorrect()) layout.getStyle().set("background-color","#99ff99")
                    ;
            else layout.getStyle().set("background-color","#ff6666")
                    ;
        }
    }
    public boolean isAnsweredExerciseListening(ExerciseListening exerciseListening, User user){
        if (exerciseListening.getAnswersListening()!=null && exerciseListening.getAnswersListening().size()!=0){
            if (exerciseListening.getAnswersListening().stream().filter(i->i.getUser().equals(user)).collect(Collectors.toList()).size()>=1){
                return true;
            }
        }
        return false;
    }

    public boolean isAnsweredExerciseGrammar(ExerciseGrammar exerciseGrammar, User user){
        if (exerciseGrammar.getAnswers()!=null && exerciseGrammar.getAnswersGrammar().size()!=0){
            if (exerciseGrammar.getAnswersGrammar().stream().filter(i->i.getUser().equals(user)).collect(Collectors.toList()).size()==1){
                return true;
            }
        }
        return false;
    }
    public boolean isAnsweredExerciseVocabulary(ExerciseVocabulary exerciseVocabulary, User user){
        if (exerciseVocabulary.getAnswers()!=null && exerciseVocabulary.getAnswers().size()!=0){
            if (exerciseVocabulary.getAnswers().stream().filter(i->i.getUser().equals(user)).collect(Collectors.toList()).size()==1){
                return true;
            }
        }
        return false;
    }
    public boolean isAnsweredExerciseReading(ExerciseReading exerciseReading, User user){
        if (exerciseReading.getAnswersReading()!=null && exerciseReading.getAnswersReading().size()!=0){
            if (exerciseReading.getAnswersReading().stream().filter(i -> i.getUser().equals(user)).count() ==1){
                return true;
            }
        }
        return false;
    }
}
