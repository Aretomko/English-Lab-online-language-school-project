package main.views.teamsAdminTest;

import com.example.application.Application;
import com.example.application.domain.Course;
import com.example.application.domain.Team;
import com.example.application.service.CourseService;
import com.example.application.service.TeamService;
import com.example.application.views.admin.teamsAdmin.CreateTeamComponent;
import com.vaadin.flow.component.grid.Grid;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Comparator;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class CreateTeamComponentTest {
    @Autowired
    private CourseService courseService;
    @Autowired
    private TeamService teamService;
    
    @Test
    public void createTeam_newTeamObjectAdded(){
        CreateTeamComponent createTeamComponent = new CreateTeamComponent(courseService, teamService, new Grid<Team>());
        int numberOfUsersBeforeMethodCall = teamService.getAllTeams().size();
        createTeamComponent.getTeamNameTextField().setValue("team name");
        createTeamComponent.getTeamScheduleTextField().setValue("team schedule");
        createTeamComponent.getCourseSelect().setValue(courseService.getAllCourses().get(0).getName());
        createTeamComponent.createTeam();
        int numberOfUsersAfterMethodCall = teamService.getAllTeams().size();
        List<Team> teams = teamService.getAllTeams();
        teams.sort(Comparator.comparing(Team::getId).reversed());
        teamService.deleteTeamByName(teams.get(0).getName());
        Assert.assertEquals(numberOfUsersAfterMethodCall-1, numberOfUsersBeforeMethodCall);
    }
    @Test
    public void createTeam_RightTeamNameAssigned(){
        CreateTeamComponent createTeamComponent = new CreateTeamComponent(courseService, teamService, new Grid<Team>());
        String teamName = "team name";
        String teamSchedule = "team schedule";
        createTeamComponent.getTeamNameTextField().setValue(teamName);
        createTeamComponent.getTeamScheduleTextField().setValue(teamSchedule);
        createTeamComponent.getCourseSelect().setValue(courseService.getAllCourses().get(0).getName());
        createTeamComponent.createTeam();
        List<Team> teams = teamService.getAllTeams();
        teams.sort(Comparator.comparing(Team::getId).reversed());
        teamService.deleteTeamByName(teams.get(0).getName());
        Assert.assertEquals(teams.get(0).getName(), teamName);
    }
    @Test
    public void createTeam_RightTeamScheduleAssigned(){
        CreateTeamComponent createTeamComponent = new CreateTeamComponent(courseService, teamService, new Grid<Team>());
        String teamName = "team name";
        String teamSchedule = "team schedule";
        createTeamComponent.getTeamNameTextField().setValue(teamName);
        createTeamComponent.getTeamScheduleTextField().setValue(teamSchedule);
        createTeamComponent.getCourseSelect().setValue(courseService.getAllCourses().get(0).getName());
        createTeamComponent.createTeam();
        List<Team> teams = teamService.getAllTeams();
        teams.sort(Comparator.comparing(Team::getId).reversed());
        teamService.deleteTeamByName(teams.get(0).getName());
        Assert.assertEquals(teams.get(0).getSchedule(), teamSchedule);
    }
    @Test
    public void createTeam_RightCourseAssigned(){
        CreateTeamComponent createTeamComponent = new CreateTeamComponent(courseService, teamService, new Grid<Team>());
        String teamName = "team name";
        String teamSchedule = "team schedule";
        String courseName = courseService.getAllCourses().get(0).getName();
        createTeamComponent.getTeamNameTextField().setValue(teamName);
        createTeamComponent.getTeamScheduleTextField().setValue(teamSchedule);
        createTeamComponent.getCourseSelect().setValue(courseName);
        createTeamComponent.createTeam();
        List<Team> teams = teamService.getAllTeams();
        teams.sort(Comparator.comparing(Team::getId).reversed());
        teamService.deleteTeamByName(teams.get(0).getName());
        Assert.assertEquals(teams.get(0).getCourse().getName(), courseName);
    }

}
