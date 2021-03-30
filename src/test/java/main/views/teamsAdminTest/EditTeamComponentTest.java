package main.views.teamsAdminTest;

import com.example.application.Application;
import com.example.application.domain.Team;
import com.example.application.service.CourseService;
import com.example.application.service.TeamService;
import com.example.application.views.admin.NavbarAdmin;
import com.example.application.views.admin.teamsAdmin.CreateGridTeamService;
import com.example.application.views.admin.teamsAdmin.CreateTeamComponent;
import com.example.application.views.admin.teamsAdmin.EditTeamComponent;
import com.example.application.views.admin.teamsAdmin.TeamsAdminView;
import com.vaadin.flow.component.grid.Grid;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class EditTeamComponentTest {
    @Autowired
    private CourseService courseService;
    @Autowired
    private TeamService teamService;
    @Autowired
    private CreateGridTeamService createGridTeamService;



    @Test
    public void updateTeam_TeamNameUpdated(){
        Team team = teamService.getAllTeams().get(0);
        String oldTeamName = team.getName();
        Grid<Team> grid = createGridTeamService.createGridTeams();
        EditTeamComponent editTeamComponent = new EditTeamComponent(team,
                courseService,
                teamService,
                grid,
                new TeamsAdminView(createGridTeamService, courseService, teamService),
                new CreateTeamComponent(courseService, teamService ,grid ),
                new NavbarAdmin());
        String newTeamName = "edited team name";
        editTeamComponent.getTeamNameTextField().setValue(newTeamName);
        editTeamComponent.updateTeam(team);
        String teamNameAfterUpdate = team.getName();
        team.setName(oldTeamName);
        teamService.save(team);
        Assert.assertEquals(newTeamName, teamNameAfterUpdate);
    }
    @Test
    public void updateTeam_TeamScheduleUpdated(){
        Team team = teamService.getAllTeams().get(0);
        String oldTeamSchedule = team.getSchedule();
        Grid<Team> grid = createGridTeamService.createGridTeams();
        EditTeamComponent editTeamComponent = new EditTeamComponent(team,
                courseService,
                teamService,
                grid,
                new TeamsAdminView(createGridTeamService, courseService, teamService),
                new CreateTeamComponent(courseService, teamService ,grid ),
                new NavbarAdmin());
        String newTeamSchedule = "edited team schedule";
        editTeamComponent.getTeamScheduleTextField().setValue(newTeamSchedule);
        editTeamComponent.updateTeam(team);
        String teamScheduleAfterUpdate = team.getSchedule();
        team.setSchedule(oldTeamSchedule);
        teamService.save(team);
        Assert.assertEquals(newTeamSchedule, teamScheduleAfterUpdate);
    }


}
