package main.views.usersAdmin;

import com.example.application.Application;
import com.example.application.domain.User;
import com.example.application.service.TeamService;
import com.example.application.service.UserService;
import com.example.application.views.admin.usersAdmin.CreateUserComponent;
import com.vaadin.flow.component.grid.Grid;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Comparator;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class CreateUserComponentTest {
    @Autowired
    private TeamService teamService;
    @Autowired
    private UserService userService;

    @Test
    public void createUserTest(){
        CreateUserComponent createUserComponent = new CreateUserComponent(teamService, userService, new Grid<User>());
        String username = "name";
        createUserComponent.getUserNameTextField().setValue(username);
        createUserComponent.getTeamSelect().setValue(teamService.getAllTeamsNames().get(0));
        int numberOfUsers = userService.getAllUsers().size();
        createUserComponent.createUser();
        int numberOfUsersAfterMethodCall = userService.getAllUsers().size();
        List<User> users  = userService.getAllUsers();
        users.sort(Comparator.comparing(User::getId).reversed());
        userService.deleteUserByUsername(users.get(0).getUsername());
        Assert.assertEquals(numberOfUsers+1, numberOfUsersAfterMethodCall);
    }
}
