package main.views.usersAdmin;

import com.example.application.Application;
import com.example.application.domain.User;
import com.example.application.service.*;
import com.example.application.views.admin.usersAdmin.CreateGridUserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Comparator;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class CreateGridUsersService {
    @Autowired
    private UserService userService;
    @Autowired
    private TeamService teamService;
    @Autowired
    private AnswerGrammarService answerGrammarService;
    @Autowired
    private AnswerReadingService answerReadingService;
    @Autowired
    private AnswerVocabularyService answerVocabularyService;
    @Autowired
    private AnswersListeningService answersListeningService;
    @Test
    public void removeButtonUsers_UsersObjectDeleted(){
        CreateGridUserService createGridUserService = new CreateGridUserService(userService,
                teamService,
                answerVocabularyService,
                answersListeningService,
                answerReadingService,
                answerGrammarService);
        createGridUserService.createGridUsers();
        userService.save(new User("name", "surname", "email", teamService.getAllTeams().get(0)));
        int numberOfUsersAfterCreationOfNew = userService.getAllUsers().size();
        List<User> users = userService.getAllUsers();
        //sort users in reverse order by id, id is allays iincreasingso we get lat created user on the top of the list
        users.sort(Comparator.comparing(User::getId).reversed());
        createGridUserService.deleteUser(users.get(0));
        int numberOfUsersAfterDeletion = userService.getAllUsers().size();
        Assert.assertEquals(numberOfUsersAfterCreationOfNew-1, numberOfUsersAfterDeletion);
    }
}
