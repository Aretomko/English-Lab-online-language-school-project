package com.example.application.service;

import com.example.application.domain.Role;
import com.example.application.domain.User;
import com.example.application.repos.UserRepo;
import com.example.application.views.admin.exercises.grammarExercises.GrammarExercisesAdminView;
import com.example.application.views.admin.exercises.listeningExercise.ListeningExercisesAdminView;
import com.example.application.views.admin.exercises.listeningTasks.ListeningTasksAdminView;
import com.example.application.views.admin.exercises.readingExercises.ReadingExercisesAdminView;
import com.example.application.views.admin.exercises.readingTasks.ReadingTasksAdminView;
import com.example.application.views.admin.exercises.readingTasks.TextEditorReadingView;
import com.example.application.views.admin.exercises.vocabularyExercises.VocabularyExerciseAdminAdmin;
import com.example.application.views.admin.lessonsAdmin.LessonsAdminView;
import com.example.application.views.admin.MainAdminView;
import com.example.application.views.admin.coursesAdmin.CoursesAdminView;
import com.example.application.views.admin.teamsAdmin.TeamsAdminView;
import com.example.application.views.admin.usersAdmin.UsersAdminView;
import com.example.application.views.answers.grammar.grammarAnswers.GrammarAnswersViewAnswersAdmin;
import com.example.application.views.answers.grammar.grammarExercises.GrammarExerciseViewAdminAnswers;
import com.example.application.views.answers.LessonsViewAnswersAdmin;
import com.example.application.views.answers.QuestionTypesViewAnswersAdmin;
import com.example.application.views.answers.listening.listeningTasks.ListeningTasksViewAdminAnswers;
import com.example.application.views.answers.listening.listenitingAnswers.ListeningAnswersViewAdminAnswers;
import com.example.application.views.answers.listening.listenitngExercises.ListeningExercisesViewAdminAnswers;
import com.example.application.views.answers.reading.readingAnswers.ReadingAnswersViewAdminAnswers;
import com.example.application.views.answers.reading.readingExercises.ReadingExercisesViewAdminAnswers;
import com.example.application.views.answers.reading.readingTasks.ReadingTasksViewAdminAnswers;
import com.example.application.views.answers.vocabluary.vocabularyAnswers.VocabularyAnswersViewAnswersAdmin;
import com.example.application.views.answers.vocabluary.vocabularyExercise.VocabularyExerciseViewAdminAnswers;
import com.example.application.views.user.MainView;
import com.example.application.views.user.courseOverview.CourseOverViewMain;
import com.example.application.views.user.grammarOverview.GrammarOverviewMain;
import com.example.application.views.user.lessonOverview.LessonOverviewMain;
import com.example.application.views.user.listening.ListeningExercisesMain;
import com.example.application.views.user.listening.ListeningTasksMain;
import com.example.application.views.user.reading.ReadingExercisesMain;
import com.example.application.views.user.reading.ReadingTasksMain;
import com.example.application.views.user.vocabulary.VocabularyExerciseMain;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.router.RouteConfiguration;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthService {
    private final UserRepo userRepo;

    public void logOut(String username) {
        UI.getCurrent().getPage().setLocation("login");
        VaadinSession.getCurrent().getSession().invalidate();
        VaadinSession.getCurrent().close();
    }

    public static class AuthorisedRoute{
        public String path;
        public Class<?extends Component> view;

        public AuthorisedRoute(String path, Class<?extends Component> view) {
            this.path =path;
            this.view = view;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public Class<? extends Component> getView() {
            return view;
        }

        public void setView(Class<? extends Component> view) {
            this.view = view;
        }
    }

    public AuthService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    public boolean authenticate(String username, String password){
        User user = userRepo.findByUsername(username);
        if(user!=null && user.getPassword().equals(password)){
            getRoutesForRole((Role) user.getRoles().toArray()[0]).forEach(route -> RouteConfiguration.forSessionScope().setRoute(route.path, route.view));
            VaadinSession.getCurrent().setAttribute("username", username);
            return true;
        }
        else return false;
    }

    public List<AuthorisedRoute> getRoutesForRole(Role role){
        ArrayList<AuthorisedRoute> routes = new ArrayList<>();
        if(role.equals(Role.USER)){
            routes.add(new AuthorisedRoute("personal", MainView.class));
            routes.add(new AuthorisedRoute("course", CourseOverViewMain.class));
            routes.add(new AuthorisedRoute("lesson", LessonOverviewMain.class));
            routes.add(new AuthorisedRoute("lesson/grammar", GrammarOverviewMain.class));
            routes.add(new AuthorisedRoute("lesson/vocabulary", VocabularyExerciseMain.class));
            routes.add(new AuthorisedRoute("lesson/reading", ReadingTasksMain.class));
            routes.add(new AuthorisedRoute("lesson/reading/exercises", ReadingExercisesMain.class));
            routes.add(new AuthorisedRoute("lesson/listening", ListeningTasksMain.class));
            routes.add(new AuthorisedRoute("lesson/listening/exercises", ListeningExercisesMain.class));
        }else {
            routes.add(new AuthorisedRoute("personal", MainView.class));
            routes.add(new AuthorisedRoute("admin", MainAdminView.class));
            routes.add(new AuthorisedRoute("admin/users", UsersAdminView.class));
            routes.add(new AuthorisedRoute("admin/groups", TeamsAdminView.class));
            routes.add(new AuthorisedRoute("admin/courses", CoursesAdminView.class));
            routes.add(new AuthorisedRoute("admin/lessons", LessonsAdminView.class));
            routes.add(new AuthorisedRoute("admin/readingTasks", ReadingTasksAdminView.class));
            routes.add(new AuthorisedRoute("admin/listeningTasks", ListeningTasksAdminView.class));
            routes.add(new AuthorisedRoute("admin/readingExercises", ReadingExercisesAdminView.class));
            routes.add(new AuthorisedRoute("admin/listeningExercises", ListeningExercisesAdminView.class));
            routes.add(new AuthorisedRoute("admin/grammarExercises", GrammarExercisesAdminView.class));
            routes.add(new AuthorisedRoute("admin/vocabularyExercises", VocabularyExerciseAdminAdmin.class));
            routes.add(new AuthorisedRoute("admin/answers", LessonsViewAnswersAdmin.class));
            routes.add(new AuthorisedRoute("admin/answers/questions", QuestionTypesViewAnswersAdmin.class));
            routes.add(new AuthorisedRoute("admin/answers/questions/grammar", GrammarExerciseViewAdminAnswers.class));
            routes.add(new AuthorisedRoute("admin/answers/answers/grammar", GrammarAnswersViewAnswersAdmin.class));
            routes.add(new AuthorisedRoute("admin/answers/answers/vocabulary", VocabularyAnswersViewAnswersAdmin.class));
            routes.add(new AuthorisedRoute("admin/answers/questions/vocabulary", VocabularyExerciseViewAdminAnswers.class));
            routes.add(new AuthorisedRoute("admin/answers/task/reading", ReadingTasksViewAdminAnswers.class));
            routes.add(new AuthorisedRoute("admin/answers/questions/reading", ReadingExercisesViewAdminAnswers.class));
            routes.add(new AuthorisedRoute("admin/answers/answers/reading", ReadingAnswersViewAdminAnswers.class));
            routes.add(new AuthorisedRoute("admin/answers/task/listening", ListeningTasksViewAdminAnswers.class));
            routes.add(new AuthorisedRoute("admin/answers/questions/listening", ListeningExercisesViewAdminAnswers.class));
            routes.add(new AuthorisedRoute("admin/answers/answers/listening", ListeningAnswersViewAdminAnswers.class));
            routes.add(new AuthorisedRoute("admin/editTextReading", TextEditorReadingView.class));
        }
        return routes;
    }
}
