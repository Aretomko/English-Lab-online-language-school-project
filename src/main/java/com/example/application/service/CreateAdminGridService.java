package com.example.application.service;

import com.example.application.domain.*;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.server.VaadinSession;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CreateAdminGridService {
    private final UserService userService;
    private final TeamService teamService;
    private final CourseService courseService;
    private final LessonsService lessonsService;
    private final ReadingService readingService;
    private final ListeningService listeningService;
    private final GrammarExerciseService grammarExerciseService;
    private final VocabularyExerciseService vocabularyExerciseService;
    private final ReadingExercisesService readingExercisesService;
    private final ListeningExerciseService listeningExerciseService;
    private final AnswerVocabularyService answerVocabularyService;
    private final AnswersListeningService answersListeningService;
    private final AnswerReadingService answerReadingService;
    private final AnswerGrammarService answerGrammarService;

    public CreateAdminGridService(UserService userService, TeamService teamService, CourseService courseService, LessonsService lessonsService, ReadingService readingService, ListeningService listeningService, GrammarExerciseService grammarExerciseService, VocabularyExerciseService vocabularyExerciseService, ReadingExercisesService readingExercisesService, ListeningExerciseService listeningExerciseService, AnswerVocabularyService answerVocabularyService, AnswersListeningService answersListeningService, AnswerReadingService answerReadingService, AnswerGrammarService answerGrammarService) {
        this.userService = userService;
        this.teamService = teamService;
        this.courseService = courseService;
        this.lessonsService = lessonsService;
        this.readingService = readingService;
        this.listeningService = listeningService;
        this.grammarExerciseService = grammarExerciseService;
        this.vocabularyExerciseService = vocabularyExerciseService;
        this.readingExercisesService = readingExercisesService;
        this.listeningExerciseService = listeningExerciseService;
        this.answerVocabularyService = answerVocabularyService;
        this.answersListeningService = answersListeningService;
        this.answerReadingService = answerReadingService;
        this.answerGrammarService = answerGrammarService;
    }

    public Grid<User> createGridUsers(){
        Grid<User> grid = new Grid<>();
        grid.setItems(userService.getAllUsers());
        grid.addColumn(User::getUsername).setHeader("Id");
        grid.addColumn(User::getRealN).setHeader("Name");
        grid.addColumn(User::getSurname).setHeader("Surname");
        grid.addColumn(User::getPassword).setHeader("Password");
        grid.addColumn(item -> teamService.getTemName(item)).setHeader("Select team");
        grid.addComponentColumn(item -> createRemoveButtonUsers(grid, item, userService)).setHeader("Delete user");
        return grid;
    }
    public Grid<Team> createGridTeams(){
        Grid<Team> grid = new Grid<>();
        grid.setItems(teamService.getAllTeams());
        grid.addColumn(Team::getName).setHeader("Team name");
        grid.addColumn(Team::getSchedule).setHeader("Team schedule");
        grid.addColumn(item -> courseService.getStringName(item)).setHeader("Course");
        grid.addComponentColumn(this::createAnswerInfoButton).setHeader("See answers");
        grid.addComponentColumn(item -> createRemoveButtonTeams(grid, item, teamService)).setHeader("Delete team");
        return grid;
    }

    private Button createAnswerInfoButton(Team team){
        Button button = new Button("Answers", event->{
            VaadinSession.getCurrent().setAttribute("teamId", team.getId());
            UI.getCurrent().navigate("admin/answers");
        });
        return button;
    }
    public Grid<Course> createGridCourses(){
        Grid<Course> grid = new Grid<>();
        grid.setItems(courseService.getAllCourses());
        grid.addColumn(Course::getName).setHeader("Course name");
        grid.addComponentColumn(item -> createRemoveButtonCourses(grid, item, teamService)).setHeader("Delete team");
        return grid;
    }
    public Grid<Lesson> createGridLessons(){
        Grid<Lesson> grid = new Grid<>();
        grid.setItems(lessonsService.getAllLessons());
        grid.addColumn(Lesson::getId).setHeader("Lesson id");
        grid.addColumn(Lesson::getName).setHeader("Lesson name");
        grid.addColumn(Lesson::getDescription).setHeader("Description");
        grid.addColumn(Lesson::getMeetingLink).setHeader("Meeting link");
        grid.addColumn(Lesson::getFilesLink).setHeader("GDrive link");
        try {
            grid.addColumn(item -> courseService.getCourseNameByLesson(item)).setHeader("Course");
        }catch(Exception ex){
            grid.addColumn("no course yet").setHeader("Course");
        }
        grid.addComponentColumn(item-> createRemoveButtonLessons(grid, item,lessonsService)).setHeader("Delete lesson");
        return grid;
    }

    public Grid<ExerciseVocabulary> createGridExerciseVocabulary(){
        Grid<ExerciseVocabulary> grid = new Grid<>();
        grid.setItems(vocabularyExerciseService.getAllVocabularyExercises());
        grid.addColumn(ExerciseVocabulary::getText).setHeader("Question text");
        grid.addColumn(ExerciseVocabulary::getAnswer).setHeader("Answers sep by /");
        grid.addColumn(ExerciseVocabulary::getRightAnswer).setHeader("Right answer");
        grid.addColumn(item -> lessonsService.getStringNameIdByVocabularyExercise(item)).setHeader("Lesson");
        grid.addComponentColumn(item -> createRemoveButtonExerciseVocabulary(grid, item));
        return grid;
    }

    public Grid<ExerciseReading> createGridExerciseReading(){
        Grid<ExerciseReading> grid = new Grid<>();
        grid.setItems(readingExercisesService.getAllReadingExercises());
        grid.addColumn(ExerciseReading::getAnswers).setHeader("Answer");
        grid.addColumn(ExerciseReading::getText).setHeader("Question");
        grid.addColumn(item -> readingService.getStringNameIdByReadingExercise(item)).setHeader("Reading task");
        grid.addComponentColumn(item-> createRemoveButtonExercisesReading(grid, item)).setHeader("Delete reading exercise");
        return grid;
    }

    public Grid<ExerciseListening> createGridExerciseListening(){
        Grid<ExerciseListening> grid = new Grid<>();
        grid.setItems(listeningExerciseService.getAllListeningExercises());
        grid.addColumn(ExerciseListening::getText).setHeader("Question");
        grid.addColumn(ExerciseListening::getAnswers).setHeader("Answers");
        grid.addColumn(item -> listeningService.getStringNameIdByListeningExercise(item)).setHeader("Listening task");
        grid.addColumn(item-> createRemoveButtonExercisesListening(grid,item)).setHeader("Delete listening exercise");
        return grid;
    }

    public Grid<Lesson> createLessonsGridAnswersAdmin(Team team){
        Grid<Lesson> grid = new Grid<>();
        //find items for the grid
        Set<Lesson> lessons = team.getCourse().getLessons();
        grid.setItems(lessons);
        grid.addColumn(Lesson::getName).setHeader("Lesson name");
        grid.addColumn(Lesson::getMeetingLink).setHeader("Meeting link");
        grid.addColumn(Lesson::getFilesLink).setHeader("Files link");
        grid.addComponentColumn(item-> this.createSeeQuestionsButtonAnswers(item));
        return grid;
    }

    public Button createSeeQuestionsButtonAnswers(Lesson lesson){
        Button button = new Button("See questions", event->{
            VaadinSession.getCurrent().setAttribute("lessonId", lesson.getId());
            UI.getCurrent().navigate("admin/answers/questions");
        });
        return button;
    }

    public Grid<Exercise> createQuestionTypesGridAnswersAdmin(Lesson lesson){
        Grid<Exercise> grid = new Grid();
        List<Exercise> exercises = new ArrayList<Exercise>();
        if(lesson.getExercisesGrammar().size()>0&&lesson.getExercisesGrammar().iterator().next()!=null)
                exercises.add(lesson.getExercisesGrammar().iterator().next())
                ;
        if (lesson.getExercisesVocabulary().size()>0&&lesson.getExercisesVocabulary().iterator().next()!=null)
                exercises.add(lesson.getExercisesVocabulary().iterator().next())
                ;
        if (lesson.getListening().size()>0&&lesson.getListening().iterator().next()!=null)
                exercises.add(lesson.getListening().iterator().next())
                ;
        if(lesson.getReading().size()>0&&lesson.getReading().iterator().next()!=null)
                exercises.add(lesson.getReading().iterator().next())
                ;
        grid.setItems(exercises);
        grid.addColumn(Exercise::getType).setHeader("Ex/Task type");
        grid.addComponentColumn(item-> createSeeExercisesOrTasksButton(item)).setHeader("See exercises");
        return grid;
    }

    public Grid<ExerciseGrammar> createExerciseGrammarGridByLesson(Lesson lesson){
        Grid<ExerciseGrammar> grid = new Grid<>();
        grid.setItems(lesson.getExercisesGrammar());
        grid.addColumn(ExerciseGrammar::getAnswers).setHeader("Possible answers");
        grid.addColumn(ExerciseGrammar::getRightAnswer).setHeader("Right answer");
        grid.addComponentColumn(this::createSeeTeamAnswersExercisesGrammar).setHeader("See answers");
        return grid;
    }

    public Grid<User> createUsersGridWithGrammarAnswers(List<User> users,
                                                        ExerciseGrammar ex){
        Grid<User> grid = new Grid<>();
        grid.setItems(users);
        grid.addColumn(User::getRealN).setHeader("Name");
        grid.addColumn(User::getSurname).setHeader("Surname");
        grid.addComponentColumn(item -> new Label(ex.getRightAnswer())).setHeader("Right answer");
        grid.addComponentColumn(item -> new Label(ex.getAnswers())).setHeader("Possible answers");
        grid.addComponentColumn(item -> this.getGrammarExerciseAnswer(item,ex)).setHeader("Answer");
        return grid;
    }

    public Grid<User> createUserGridWithVocabularyAnswers(List<User> users,
                                                          ExerciseVocabulary exerciseVocabulary){
        Grid<User> grid = new Grid<>();
        grid.setItems(users);
        grid.addColumn(User::getRealN).setHeader("Name");
        grid.addColumn(User::getSurname).setHeader("Surname");
        grid.addComponentColumn(item -> new Label(exerciseVocabulary.getAnswer()));
        grid.addComponentColumn(item -> new Label(exerciseVocabulary.getRightAnswer()));
        grid.addComponentColumn(item-> this.getVocabularyExerciseAnswer(item,exerciseVocabulary));
        return grid;
    }
    public Grid<ExerciseVocabulary> createExerciseVocabularyGridByLesson(Lesson lesson){
        Grid<ExerciseVocabulary> grid = new Grid<>();
        grid.setItems(lesson.getExercisesVocabulary());
        grid.addColumn(ExerciseVocabulary::getAnswer).setHeader("Possible answers");
        grid.addColumn(ExerciseVocabulary::getRightAnswer).setHeader("Right answer");
        grid.addComponentColumn(this::createSeeTeamAnswersExercisesVocabulary).setHeader("See answers");
        return grid;
    }

    public Grid<Reading> createReadingTaskGridByLesson(Lesson lesson){
        Grid<Reading> grid = new Grid<Reading>();
        grid.setItems(lesson.getReading());
        grid.addColumn(Reading::getName).setHeader("Reading task name");
        grid.addComponentColumn(item -> createSeeAnswersButtonByReading(item)).setHeader("See exercises");
        return grid;
    }

    public Button createSeeAnswersButtonByReading(Reading reading){
        return new Button("See exercises", event->{
            VaadinSession.getCurrent().setAttribute("readingId", reading.getId());
            UI.getCurrent().navigate("admin/answers/questions/reading");
        });
    }

    public Grid<ExerciseReading> createReadingExercisesGridByReading(Reading reading){
        Grid<ExerciseReading> grid = new Grid<>();
        grid.setItems(reading.getExercisesReading());
        grid.addColumn(ExerciseReading::getAnswers).setHeader("Possible answers");
        grid.addColumn(ExerciseReading::getRightAnswer).setHeader("Right answer");
        grid.addComponentColumn(item->createSeeAnswersButtonExerciseReading(item));
        return grid;
    }
    public Button createSeeAnswersButtonExerciseReading(ExerciseReading exerciseReading){
        Button button = new Button("See answers", event-> {
            VaadinSession.getCurrent().setAttribute("exerciseId", exerciseReading.getId());
            UI.getCurrent().navigate("admin/answers/answers/reading");
        });
        return button;
    }

    public Grid<AnswerReading> createAnswersReadingGridByReading(ExerciseReading exerciseReading){
        Grid<AnswerReading> grid = new Grid<>();
        grid.setItems(exerciseReading.getAnswersReading());
        grid.addColumn(item -> item.getUser().getRealN()).setHeader("Student name");
        grid.addColumn(item -> item.getUser().getSurname()).setHeader("Student surname");
        grid.addColumn(AnswerReading::getAnswer).setHeader("Users answer");
        grid.addColumn(item->item.getExerciseReading().getRightAnswer()).setHeader("Right answer");
        grid.addColumn(item -> item.getExerciseReading().getAnswers()).setHeader("Possible answers");
        return grid;
    }

    private Button createSeeTeamAnswersExercisesVocabulary(ExerciseVocabulary exerciseVocabulary){
        Button button = new Button("See answers", event-> {
            VaadinSession.getCurrent().setAttribute("vocabularyExerciseId", exerciseVocabulary.getId());
            UI.getCurrent().navigate("admin/answers/answers/vocabulary");
        });
        return button;
    }



    public Component getVocabularyExerciseAnswer(User user,
                                                 ExerciseVocabulary exerciseVocabulary){
        List<Answer> answers = user.getAnswersVocabulary()
                .stream()
                .filter(i-> !i.getExerciseVocabulary().equals(exerciseVocabulary))
                .collect(Collectors.toList());
        return receiveOutputAnswers(answers,exerciseVocabulary);
    }

    private Component getGrammarExerciseAnswer(User user,
                                               ExerciseGrammar exerciseGrammar){
        List<Answer> answers = user.getAnswersGrammar()
                .stream()
                .filter(i->i.getExerciseGrammar().equals(exerciseGrammar))
                .collect(Collectors.toList());
        return receiveOutputAnswers(answers,exerciseGrammar);
    }

    private Component receiveOutputAnswers(List<Answer> answers,
                                           Exercise exercise){
        if (answers.size()>1) return new Label("Internal error")
                ;
        if (answers.size()==0) return new Label("Not answered yet");
        if (answers.get(0).getAnswer().equals(exercise.getRightAnswer())) {
            return new Label(answers.get(0).getAnswer() + " true");
        }else{
            return new Label(answers.get(0).getAnswer() + " false");
        }
    }

    private Button createSeeTeamAnswersExercisesGrammar(ExerciseGrammar exerciseGrammar){
        Button button = new Button("See answers", event-> {
            VaadinSession.getCurrent().setAttribute("grammarExerciseId", exerciseGrammar.getId());
            UI.getCurrent().navigate("admin/answers/answers/grammar");
        });
        return button;
    }

    public Button createSeeExercisesOrTasksButton(Exercise exercise){
        Button button = new Button("See exercises", event->{
            VaadinSession.getCurrent().setAttribute("exerciseId", exercise.getId());
            UI.getCurrent().navigate(exercise.getAnswersLink());
        });
        return button;
    }

    public Button createRemoveButtonExercisesReading(Grid<ExerciseReading> grid, ExerciseReading exerciseReading){
        Button button = new Button("Delete", event-> {
            ListDataProvider<ExerciseReading> dataProvider = (ListDataProvider<ExerciseReading>) grid.getDataProvider();
            dataProvider.getItems().remove(exerciseReading);
            readingExercisesService.delete(exerciseReading);
            dataProvider.refreshAll();
        });
        return button;
    }
    public Button createRemoveButtonExerciseVocabulary(Grid<ExerciseVocabulary> grid, ExerciseVocabulary exerciseVocabulary){
        Button button = new Button("Delete", event->{
            ListDataProvider<ExerciseVocabulary> dataProvider = (ListDataProvider<ExerciseVocabulary>) grid.getDataProvider();
            for(AnswerVocabulary answer:exerciseVocabulary.getAnswers()){
                answerVocabularyService.delete(answer);
            }
            dataProvider.getItems().remove(exerciseVocabulary);
            vocabularyExerciseService.delete(exerciseVocabulary);
            dataProvider.refreshAll();
        });
        return button;

    }

    public Button createRemoveButtonExercisesListening(Grid<ExerciseListening> grid, ExerciseListening exerciseListening){
        Button button = new Button("Delete", event->{
            ListDataProvider<ExerciseListening> dataProvider =  (ListDataProvider<ExerciseListening>) grid.getDataProvider();
            dataProvider.getItems().remove(exerciseListening);
            listeningExerciseService.delete(exerciseListening);
            dataProvider.refreshAll();
        });
        return button;
    }


    private Button createRemoveButtonCourses(Grid<Course> grid, Course item, TeamService teamService) {
        @SuppressWarnings("unchecked")
        Button button = new Button("Delete", clickEvent -> {
            ListDataProvider<Course> dataProvider = (ListDataProvider<Course>) grid.getDataProvider();
            dataProvider.getItems().remove(item);
            courseService.delete(item);
            dataProvider.refreshAll();
        });
        return button;
    }
    private Button createRemoveButtonLessons(Grid<Lesson> grid, Lesson lesson, LessonsService lessonsService) {
        @SuppressWarnings("unchecked")
        Button button = new Button("Delete", clickEvent -> {
            ListDataProvider<Lesson> dataProvider = (ListDataProvider<Lesson>) grid.getDataProvider();
            dataProvider.getItems().remove(lesson);
            lessonsService.delete(lesson);
            dataProvider.refreshAll();
        });
        return button;
    }

    private Component createRemoveButtonTeams(Grid<Team> grid, Team item, TeamService teamService) {
        @SuppressWarnings("unchecked")
        Button button = new Button("Delete", clickEvent -> {
            ListDataProvider<Team> dataProvider = (ListDataProvider<Team>) grid.getDataProvider();
            dataProvider.getItems().remove(item);
            teamService.deleteTeamByName(item.getName());
            dataProvider.refreshAll();
        });
        return button;
    }

    private Button createRemoveButtonUsers(Grid<User> grid, User item, UserService userService) {
        @SuppressWarnings("unchecked")
        Button button = new Button("Delete", clickEvent -> {
            ListDataProvider<User> dataProvider = (ListDataProvider<User>) grid.getDataProvider();
            dataProvider.getItems().remove(item);
            userService.deleteUserByUsername(item.getUsername());
            //delete all answers connected with the user
            answerVocabularyService.deleteAll(item.getAnswersVocabulary());

            dataProvider.refreshAll();
        });
        return button;
    }
}
