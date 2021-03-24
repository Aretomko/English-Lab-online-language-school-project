package main.views;

import com.example.application.Application;
import com.example.application.domain.Course;
import com.example.application.repos.CourseRepo;
import com.example.application.service.CourseService;
import com.example.application.service.TeamService;
import com.example.application.views.admin.NavbarAdmin;
import com.example.application.views.admin.coursesAdmin.CourseEditComponent;
import com.example.application.views.admin.coursesAdmin.CoursesAdminView;
import com.example.application.views.admin.coursesAdmin.CreateCourseComponent;
import com.example.application.views.admin.coursesAdmin.CreateGridCoursesService;
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
public class CourseEditComponentTest {
    @Autowired
    private CourseService courseService;
    @Autowired
    private CourseRepo courseRepo; //used in course service
    @Autowired
    private CreateGridCoursesService createGridCoursesService;
    @Autowired
    private TeamService teamService; //used in createGridService

    @Test
    public void ConstructorTest_RightLabelApplied(){
        String courseName = "A1 course";
        Course mockCourse = new Course(courseName);
        CourseEditComponent courseEditComponent = new CourseEditComponent(mockCourse,
                courseService,
                new Grid<Course>(),
                new CoursesAdminView(createGridCoursesService, courseService),
                new NavbarAdmin(),
                new CreateCourseComponent(courseService, new Grid<Course>()));
        Assert.assertEquals(courseEditComponent.getEditLabel().getText(), "Edit course "+courseName);
    }
    @Test
    public void ConstructorTest_FontApplied(){
        String courseName = "A1 course";
        Course mockCourse = new Course(courseName);
        CourseEditComponent courseEditComponent = new CourseEditComponent(mockCourse,
                courseService,
                new Grid<Course>(),
                new CoursesAdminView(createGridCoursesService, courseService),
                new NavbarAdmin(),
                new CreateCourseComponent(courseService, new Grid<Course>()));
        Assert.assertEquals(true, courseEditComponent.getEditLabel().getStyle().get("font").equals("400 13.3333px Arial"));
    }
    @Test
    public void ConstructorTest_FontSizeApplied(){
        String courseName = "A1 course";
        Course mockCourse = new Course(courseName);
        CourseEditComponent courseEditComponent = new CourseEditComponent(mockCourse,
                courseService,
                new Grid<Course>(),
                new CoursesAdminView(createGridCoursesService, courseService),
                new NavbarAdmin(),
                new CreateCourseComponent(courseService, new Grid<Course>()));
        Assert.assertEquals(true, courseEditComponent.getEditLabel().getStyle().get("font-size").equals("var(--lumo-font-size-s)"));
    }
    @Test
    public void ConstructorTest_FontWeightApplied(){
        String courseName = "A1 course";
        Course mockCourse = new Course(courseName);
        CourseEditComponent courseEditComponent = new CourseEditComponent(mockCourse,
                courseService,
                new Grid<Course>(),
                new CoursesAdminView(createGridCoursesService, courseService),
                new NavbarAdmin(),
                new CreateCourseComponent(courseService, new Grid<Course>()));
        Assert.assertEquals(true, courseEditComponent.getEditLabel().getStyle().get("font-weight").equals("500"));
    }
    @Test
    public void ConstructorTest_CourseTextFieldWithValue(){
        String courseName = "A1 course";
        Course mockCourse = new Course(courseName);
        CourseEditComponent courseEditComponent = new CourseEditComponent(mockCourse,
                courseService,
                new Grid<Course>(),
                new CoursesAdminView(createGridCoursesService, courseService),
                new NavbarAdmin(),
                new CreateCourseComponent(courseService, new Grid<Course>()));
        Assert.assertEquals(courseName, courseEditComponent.getCourseNameTextField().getValue());
    }
    @Test
    public void ConstructorTest_SubmitButtonName(){
        String courseName = "A1 course";
        Course mockCourse = new Course(courseName);
        CourseEditComponent courseEditComponent = new CourseEditComponent(mockCourse,
                courseService,
                new Grid<Course>(),
                new CoursesAdminView(createGridCoursesService, courseService),
                new NavbarAdmin(),
                new CreateCourseComponent(courseService, new Grid<Course>()));
        Assert.assertEquals("Update", courseEditComponent.getSubmit().getText());
    }
    @Test
    public void ConstructorTest_UpdateCourse(){
        String courseName = "A1 course";
        Course mockCourse = new Course(courseName);
        CoursesAdminView coursesAdminView = new CoursesAdminView(createGridCoursesService, courseService);
        CreateCourseComponent createCourseComponent = new CreateCourseComponent(courseService, new Grid<Course>());
        CourseEditComponent courseEditComponent = new CourseEditComponent(mockCourse,
                courseService,
                new Grid<Course>(),
                coursesAdminView,
                new NavbarAdmin(),
                createCourseComponent);
        courseEditComponent.getCourseNameTextField().setValue("Some course name");
        courseEditComponent.updateCourse(mockCourse, courseService, new Grid<Course>(), coursesAdminView, new NavbarAdmin() , createCourseComponent);
        Assert.assertEquals("Some course name", courseEditComponent.getCourse().getName());
    }
    @Test
    public void ConstructorTest_UpdateCourse_(){
        String courseName = "A1 course";
        Course mockCourse = new Course(courseName);
        CoursesAdminView coursesAdminView = new CoursesAdminView(createGridCoursesService, courseService);
        CreateCourseComponent createCourseComponent = new CreateCourseComponent(courseService, new Grid<Course>());
        CourseEditComponent courseEditComponent = new CourseEditComponent(mockCourse,
                courseService,
                new Grid<Course>(),
                new CoursesAdminView(createGridCoursesService, courseService),
                new NavbarAdmin(),
                new CreateCourseComponent(courseService, new Grid<Course>()));
        int numberOfCourses = courseService.getAllCourses().size();
        courseEditComponent.updateCourse(mockCourse, courseService, new Grid<Course>(), coursesAdminView, new NavbarAdmin() , createCourseComponent);
        int numberOfCoursesAfter = courseService.getAllCourses().size();
        courseService.delete(mockCourse);
        Assert.assertEquals(numberOfCourses+1, numberOfCoursesAfter);
    }
}
