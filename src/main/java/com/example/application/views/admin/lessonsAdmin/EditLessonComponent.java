package com.example.application.views.admin.lessonsAdmin;

import com.example.application.domain.Lesson;
import com.example.application.service.CourseService;
import com.example.application.service.LessonsService;
import com.example.application.views.admin.NavbarAdmin;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.select.Select;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;

public class EditLessonComponent extends HorizontalLayout {
    public EditLessonComponent(Lesson lesson, CourseService courseService, LessonsService lessonsService, Grid<Lesson> grid, LessonsAdminView lessonsAdminView, NavbarAdmin navbarAdmin, CreateLessonComponent createLessonComponent){
        Label editLabel = new Label("Edit lesson " + lesson.getName());
        editLabel.setHeight("wrap-content");
        editLabel.getStyle().set("font","400 13.3333px Arial");
        editLabel.getStyle().set("font-size", "var(--lumo-font-size-s)");
        editLabel.getStyle().set("font-weight", "500");
        //create form field
        TextField lessonNameTextField = new TextField("name");
        TextField lessonDescriptionTextField = new TextField("surname");
        TextField lessonLinkTextField = new TextField("GDrive link");
        TextField meetingLink = new TextField("meeting link");
        if (lesson.getName()!=null) lessonNameTextField.setValue(lesson.getName())
                ;
        else lessonNameTextField.setPlaceholder("not set")
                ;
        if (lesson.getDescription()!=null) lessonDescriptionTextField.setValue(lesson.getDescription())
                ;
        else lessonDescriptionTextField.setPlaceholder("not set")
                ;
        if (lesson.getFilesLink()!=null) lessonLinkTextField.setValue(lesson.getDescription())
                ;
        else lessonLinkTextField.setPlaceholder("not set")
                ;
        if (lesson.getMeetingLink()!=null) meetingLink.setValue(lesson.getMeetingLink())
                ;
        else meetingLink.setPlaceholder("not set")
                ;
        Select<String> placeholderSelect = new Select<>();
        placeholderSelect.setItems(courseService.getAllCoursesNames());
        if (lesson.getCourse()!=null) placeholderSelect.setValue(lesson.getCourse().getName())
                ;
        else placeholderSelect.setPlaceholder("not selected")
                ;
        placeholderSelect.setLabel("course");
        //create button
        Button submit = new Button("Update" , event -> {
            lesson.setName(lessonNameTextField.getValue());
            lesson.setDescription(lessonDescriptionTextField.getValue());
            lesson.setFilesLink(lessonLinkTextField.getValue());
            lesson.setCourse(courseService.getCourseByName(placeholderSelect.getValue()));
            lesson.setMeetingLink(meetingLink.getValue());
            lessonsService.save(lesson);
            grid.getDataProvider().refreshItem(lesson);
            lessonsAdminView.removeAll();
            lessonsAdminView.add(navbarAdmin, createLessonComponent, grid);
        });
        this.add(editLabel ,lessonNameTextField,lessonDescriptionTextField,lessonLinkTextField, placeholderSelect,meetingLink,  submit);
        this.setWidth("100%");
        this.setPadding(true);
        this.setAlignItems(Alignment.BASELINE);
    }
}
