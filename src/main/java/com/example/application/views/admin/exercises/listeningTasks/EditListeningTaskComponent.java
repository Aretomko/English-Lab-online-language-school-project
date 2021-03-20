package com.example.application.views.admin.exercises.listeningTasks;

import com.example.application.domain.Listening;
import com.example.application.service.LessonsService;
import com.example.application.service.ListeningService;
import com.example.application.views.admin.NavbarAdmin;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;

public class EditListeningTaskComponent extends HorizontalLayout {
    public EditListeningTaskComponent(Listening listening,
                                      LessonsService lessonsService,
                                      ListeningService listeningService,
                                      Grid<Listening> grid,
                                      CreateListeningTaskComponent createListeningTaskComponent,
                                      NavbarAdmin navbarAdmin,
                                      ListeningTasksAdminView listeningTasksAdminView){
        Label editLabel = new Label("Edit listening task " + listening.getName());
        editLabel.setHeight("wrap-content");
        editLabel.getStyle().set("font","400 13.3333px Arial");
        editLabel.getStyle().set("font-size", "var(--lumo-font-size-s)");
        editLabel.getStyle().set("font-weight", "500");
        //create form field
        TextField userNameTextField = new TextField("name");
        TextField lessonIdTextField = new TextField("lesson id");
        if(listening.getName()!=null) userNameTextField.setValue(String.valueOf(listening.getLesson().getId()))
                ;
        else userNameTextField.setPlaceholder("not set");
        if(listening.getLesson()!=null) lessonIdTextField.setValue(String.valueOf(listening.getId()))
                ;
        else lessonIdTextField.setValue("not set");
        Button submit = new Button("Update" , event -> {
            listening.setName(userNameTextField.getValue());
            listening.setLesson(lessonsService.findLessonById(lessonIdTextField.getValue()));
            listeningService.save(listening);
            grid.getDataProvider().refreshItem(listening);
            listeningTasksAdminView.removeAll();
            listeningTasksAdminView.add(navbarAdmin, createListeningTaskComponent, grid);
        });
        this.add(editLabel ,userNameTextField, lessonIdTextField, submit);
        this.setWidth("100%");
        this.setPadding(true);
        this.setAlignItems(Alignment.BASELINE);
    }
}
