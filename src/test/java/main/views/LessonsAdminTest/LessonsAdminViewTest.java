package main.views.LessonsAdminTest;

import com.example.application.Application;
import com.example.application.service.CourseService;
import com.example.application.service.LessonsService;
import com.example.application.views.admin.lessonsAdmin.CreateGridLessonsService;
import com.example.application.views.admin.lessonsAdmin.LessonsAdminView;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@AutoConfigureMockMvc
public class LessonsAdminViewTest {
    @Autowired
    private CreateGridLessonsService createGridLessonsService;
    @Autowired
    private LessonsService lessonsService;
    @Autowired
    private CourseService courseService;

    @Test
    public void Constructor_EditLessonsComponent(){
       LessonsAdminView lessonsAdminView = new LessonsAdminView(createGridLessonsService, lessonsService, courseService);
    }


}
