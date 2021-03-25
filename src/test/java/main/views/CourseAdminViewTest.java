package main.views;

import com.example.application.Application;
import com.example.application.domain.Course;
import com.example.application.service.CourseService;
import com.example.application.service.TeamService;
import com.example.application.views.admin.NavbarAdmin;
import com.example.application.views.admin.coursesAdmin.CourseEditComponent;
import com.example.application.views.admin.coursesAdmin.CoursesAdminView;
import com.example.application.views.admin.coursesAdmin.CreateCourseComponent;
import com.example.application.views.admin.coursesAdmin.CreateGridCoursesService;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.grid.Grid;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class CourseAdminViewTest {
    @Autowired
    private CreateGridCoursesService createGridCoursesService;
    @Autowired
    private CourseService courseService; //needed for createGridCourses
    @Autowired
    private TeamService teamService; //needed for createGridCourses



    @Test
    public void Controller_ComponentsAdded(){
        CoursesAdminView coursesAdminView = new CoursesAdminView(createGridCoursesService, courseService);
        List<Component> elements =  coursesAdminView.getChildren().collect(Collectors.toList());
        Assert.assertEquals(3, elements.size());
    }

    @Test
    public void editCourseEvent_EditUserComponentAddedAfterClick(){
        CoursesAdminView coursesAdminView = new CoursesAdminView(createGridCoursesService, courseService);
        coursesAdminView.editCourseEvent(new Course("Some course"), new CreateCourseComponent(courseService, new Grid<Course>()));
        List<Component> elements =  coursesAdminView.getChildren().collect(Collectors.toList());

        Assert.assertTrue(elements.get(1).getElement().toString().equals(new CourseEditComponent(new Course("Some course"),
                courseService,
                new Grid<Course>(),
                coursesAdminView,
                new NavbarAdmin(),
                new CreateCourseComponent(courseService, new Grid<Course>())).getElement().toString()));
    }

}
