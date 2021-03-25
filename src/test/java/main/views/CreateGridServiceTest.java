package main.views;

import com.example.application.Application;
import com.example.application.domain.Course;
import com.example.application.service.CourseService;
import com.example.application.service.TeamService;
import com.example.application.views.admin.coursesAdmin.CreateGridCoursesService;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.data.provider.Query;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class CreateGridServiceTest {
    @Autowired
    private CourseService courseService;
    @Autowired
    private TeamService teamService;

    @Test
    public void CreateGridCourses_EntityForEveryCourseCreated(){
        CreateGridCoursesService createGridCoursesService = new CreateGridCoursesService(courseService, teamService);
        int numberOfCourses = courseService.getAllCourses().size();
        Grid<Course> grid = createGridCoursesService.createGridCourses();
        int numberOfEntitiesInGRid = grid.getDataProvider().fetch(new Query<>()).collect(Collectors.toList()).size();
        Assert.assertEquals(numberOfCourses, numberOfEntitiesInGRid);
    }
}
