package com.example.application.views.admin.usersAdmin;

import com.example.application.domain.User;
import com.example.application.service.*;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.data.provider.ListDataProvider;
import org.springframework.stereotype.Service;

@Service
public class CreateGridUserService {
    private final UserService userService;
    private final TeamService teamService;
    private final AnswerVocabularyService answerVocabularyService;
    private final AnswersListeningService answersListeningService;
    private final AnswerReadingService answerReadingService;
    private final AnswerGrammarService answerGrammarService;

    //grid
    private Grid<User> grid;

    public CreateGridUserService(UserService userService, TeamService teamService, AnswerVocabularyService answerVocabularyService, AnswersListeningService answersListeningService, AnswerReadingService answerReadingService, AnswerGrammarService answerGrammarService) {
        this.userService = userService;
        this.teamService = teamService;
        this.answerVocabularyService = answerVocabularyService;
        this.answersListeningService = answersListeningService;
        this.answerReadingService = answerReadingService;
        this.answerGrammarService = answerGrammarService;
    }

    public Grid<User> createGridUsers(){
        this.grid = new Grid<>();
        grid.setItems(userService.getAllUsers());
        grid.addColumn(User::getUsername).setHeader("Id");
        grid.addColumn(User::getRealN).setHeader("Name");
        grid.addColumn(User::getSurname).setHeader("Surname");
        grid.addColumn(User::getPassword).setHeader("Password");
        grid.addColumn(item -> teamService.getTemName(item)).setHeader("Select team");
        grid.addComponentColumn(this::createRemoveButtonUsers).setHeader("Delete user");
        return grid;
    }


    private Button createRemoveButtonUsers(User item) {
        @SuppressWarnings("unchecked")
        Button button = new Button("Delete", clickEvent -> {
            deleteUser(item);
        });
        return button;
    }

    public void deleteUser(User item){
        ListDataProvider<User> dataProvider = (ListDataProvider<User>) grid.getDataProvider();
        dataProvider.getItems().remove(item);
        //delete all answers connected with the user
        answerVocabularyService.deleteAll(item.getAnswersVocabulary());
        answerGrammarService.deleteAll(item.getAnswersGrammar());
        answerReadingService.deleteAll(item.getAnswersReading());
        answersListeningService.deleteAll(item.getAnswersListening());
        //delete User itself
        userService.deleteUserByUsername(item.getUsername());
        dataProvider.refreshAll();
    }
    public Grid<User> getGrid() {
        return grid;
    }
}
