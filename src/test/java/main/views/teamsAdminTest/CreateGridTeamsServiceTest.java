package main.views.teamsAdminTest;

import com.example.application.Application;
import com.example.application.domain.Team;
import com.example.application.service.CourseService;
import com.example.application.service.TeamService;
import com.example.application.views.admin.teamsAdmin.CreateGridTeamService;
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
public class CreateGridTeamsServiceTest {
    @Autowired
    private TeamService teamService;
    @Autowired
    private CourseService courseService;

    @Test
    public void removeButtonTeams_TeamObjectDeleted(){
        CreateGridTeamService createGridTeamService = new CreateGridTeamService(teamService, courseService);
        createGridTeamService.createGridTeams();
        teamService.save(new Team());
        int numberOfTeamsAfterAddingNewTeam = teamService.getAllTeams().size();
        List<Team> teams = teamService.getAllTeams();
        teams.sort(Comparator.comparing(Team::getId).reversed());
        createGridTeamService.deleteTeam(teams.get(0));
        int numberOfTeamsAfterDeleteMethod = teamService.getAllTeams().size();
        Assert.assertEquals(numberOfTeamsAfterAddingNewTeam-1, numberOfTeamsAfterDeleteMethod);
    }
}
