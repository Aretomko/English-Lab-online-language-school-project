package com.example.application.views.admin.exercises.listeningTasks;

import com.example.application.domain.Listening;
import com.example.application.domain.User;
import com.example.application.service.LessonsService;
import com.example.application.service.ListeningService;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;



public class CreateListeningTaskComponent extends HorizontalLayout {
    private final ListeningService listeningService;
    private final LessonsService lessonsService;
    private final Grid<Listening> grid;
    private Label editLabel;
    private TextField listeningNameTextField;
    private TextField lessonIdTextField;
    private Checkbox isHomework;
    private Button submit;

    public CreateListeningTaskComponent(ListeningService listeningService, LessonsService lessonsService, Grid<Listening> grid){
        this.listeningService = listeningService;
        this.lessonsService = lessonsService;
        this.grid= grid;
        //UI components initialization
        this.editLabel = new Label("Create new listening task");
        editLabel.setHeight("wrap-content");
        editLabel.getStyle().set("font","400 13.3333px Arial");
        editLabel.getStyle().set("font-size", "var(--lumo-font-size-s)");
        editLabel.getStyle().set("font-weight", "500");
        //create form field
        this.listeningNameTextField = new TextField("name");
        this.lessonIdTextField = new TextField("lesson id");
        this.isHomework = new Checkbox("is homework");
        //create button
        this.submit = new Button("Create" , event -> {
            this.createListening();
        });
        this.add(editLabel ,listeningNameTextField, lessonIdTextField,isHomework, submit);
        this.setWidth("100%");
        this.setPadding(true);
        this.setAlignItems(Alignment.BASELINE);
    }
    public void createListening(){
        try {
            listeningService.save(new Listening(listeningNameTextField.getValue(), lessonsService.findLessonById(lessonIdTextField.getValue()), isHomework.getValue()));
        }catch (RuntimeException ex){
            Notification.show("Wrong id provided! Id should be just an integer");
        }
        grid.setItems(listeningService.getAllListings());
        listeningNameTextField.setValue("");
        lessonIdTextField.setValue("");
        isHomework.setValue(false);
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

    public Checkbox getIsHomework() {
        return isHomework;
    }

    public Button getSubmit() {
        return submit;
    }
}
