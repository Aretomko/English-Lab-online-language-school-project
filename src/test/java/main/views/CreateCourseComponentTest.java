package main.views;

import com.example.application.Application;
import com.example.application.domain.Course;
import com.example.application.service.CourseService;
import com.example.application.views.admin.coursesAdmin.CreateCourseComponent;
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
public class CreateCourseComponentTest {
    @Autowired
    private CourseService courseService;
    @Test
    public void Controller_LabelTextInitialized(){
        CreateCourseComponent createCourseComponent = new CreateCourseComponent(courseService, new Grid<Course>());
        Assert.assertEquals("Create new course", createCourseComponent.getEditLabel().getText());
    }
    @Test
    public void Controller_RightLabelFontStyleSet(){
        CreateCourseComponent createCourseComponent = new CreateCourseComponent(courseService, new Grid<Course>());
        Assert.assertEquals("400 13.3333px Arial", createCourseComponent.getEditLabel().getStyle().get("font"));
    }
    @Test
    public void Controller_RightLabelFontSizeSet(){

        CreateCourseComponent createCourseComponent = new CreateCourseComponent(courseService, new Grid<Course>());
        Assert.assertEquals("var(--lumo-font-size-s)", createCourseComponent.getEditLabel().getStyle().get("font-size"));
    }
    @Test
    public void Controller_RightLabelFontWeightSet(){

        CreateCourseComponent createCourseComponent = new CreateCourseComponent(courseService, new Grid<Course>());
        Assert.assertEquals("500", createCourseComponent.getEditLabel().getStyle().get("font-weight"));
    }
    @Test
    public void Controller_courseNameTextFieldLabelSet(){

        CreateCourseComponent createCourseComponent = new CreateCourseComponent(courseService, new Grid<Course>());
        Assert.assertEquals("course name", createCourseComponent.getCourseNameTextField().getLabel());
    }
    @Test
    public void createCourse_normalCourseNameProvided_NewCourseCreated(){
        CreateCourseComponent createCourseComponent = new CreateCourseComponent(courseService, new Grid<Course>());
        String courseName = "Course name 1";
        createCourseComponent.getCourseNameTextField().setValue(courseName);
        createCourseComponent.createCourse();
        boolean isCourseCreated = courseService.getAllCoursesNames().contains(courseName);
        courseService.delete(courseService.getCourseByName(courseName));
        Assert.assertTrue(isCourseCreated);
    }
}
