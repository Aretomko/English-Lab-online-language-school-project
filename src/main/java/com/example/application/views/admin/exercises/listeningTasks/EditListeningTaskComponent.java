package com.example.application.views.admin.exercises.listeningTasks;

import com.example.application.domain.Listening;
import com.example.application.service.LessonsService;
import com.example.application.service.ListeningService;
import com.example.application.views.admin.NavbarAdmin;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;

public class EditListeningTaskComponent extends HorizontalLayout {
    private final LessonsService lessonsService;
    private final ListeningService listeningService;
    private final Grid<Listening> grid;
    private final CreateListeningTaskComponent createListeningTaskComponent;
    private final NavbarAdmin navbarAdmin;
    private final ListeningTasksAdminView listeningTasksAdminView;
    //UI components
    private Label editLabel;
    private TextField listeningNameTextField;
    private TextField lessonIdTextField;
    private TextField getLessonIdTextField;
    private Checkbox isHomework;
    private Button submit;

    public EditListeningTaskComponent(Listening listening,
                                      LessonsService lessonsService,
                                      ListeningService listeningService,
                                      Grid<Listening> grid,
                                      CreateListeningTaskComponent createListeningTaskComponent,
                                      NavbarAdmin navbarAdmin,
                                      ListeningTasksAdminView listeningTasksAdminView){
        this.listeningService = listeningService;
        this.lessonsService = lessonsService;
        this.createListeningTaskComponent = createListeningTaskComponent;
        this.navbarAdmin = navbarAdmin;
        this.listeningTasksAdminView = listeningTasksAdminView;
        this.grid = grid;
        //inotialize UI compoents
        this.editLabel = new Label("Edit listening task " + listening.getName());
        editLabel.setHeight("wrap-content");
        editLabel.getStyle().set("font","400 13.3333px Arial");
        editLabel.getStyle().set("font-size", "var(--lumo-font-size-s)");
        editLabel.getStyle().set("font-weight", "500");
        //create form field
        this.listeningNameTextField = new TextField("name");
        this.lessonIdTextField = new TextField("lesson id");
        this.isHomework = new Checkbox("Is homework");
        if(listening.getName()!=null) listeningNameTextField.setValue(String.valueOf(listening.getLesson().getId()))
                ;
        else listeningNameTextField.setPlaceholder("not set")
                ;
        if(listening.getLesson()!=null) lessonIdTextField.setValue(String.valueOf(listening.getId()))
                ;
        else lessonIdTextField.setValue("not set")
                ;
        if(listening.getHomework()!=null) isHomework.setValue(listening.getHomework())
                ;
        else isHomework.setValue(false)
                ;
        this.submit = new Button("Update" , event -> {
            listening.setName(listeningNameTextField.getValue());
            listening.setLesson(lessonsService.findLessonById(lessonIdTextField.getValue()));
            listening.setHomework(isHomework.getValue());
            listeningService.save(listening);
            grid.getDataProvider().refreshItem(listening);
            listeningTasksAdminView.removeAll();
            listeningTasksAdminView.add(navbarAdmin, createListeningTaskComponent, grid);
        });
        this.add(editLabel ,listeningTasksAdminView, lessonIdTextField,isHomework, submit);
        this.setWidth("100%");
        this.setPadding(true);
        this.setAlignItems(Alignment.BASELINE);
    }

    public Label getEditLabel() {
        return editLabel;
    }

    public TextField getListeningNameTextField() {
        return listeningNameTextField;
    }

    public TextField getLessonIdTextField() {
        return lessonIdTextField;
    }

    public TextField getGetLessonIdTextField() {
        return getLessonIdTextField;
    }

    public Checkbox getIsHomework() {
        return isHomework;
    }

    public Button getSubmit() {
        return submit;
    }
}
