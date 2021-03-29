package com.example.application.views.user.lessonOverview;

import com.example.application.domain.Lesson;
import com.example.application.service.LessonsService;
import com.example.application.views.navbar.NavbarView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.server.VaadinSession;


public class LessonOverviewMain extends VerticalLayout {
    public LessonOverviewMain(LessonsService lessonsService){
        NavbarView navbarView = new NavbarView();
        this.add(navbarView);
        this.setWidth("100%");
        this.setAlignItems(Alignment.CENTER);
        String lessonId = VaadinSession.getCurrent().getAttribute("lessonId").toString();
        Lesson lesson = lessonsService.findLessonById(lessonId);
        //create wrapper layout
        VerticalLayout wrapper = new VerticalLayout();
        wrapper.setAlignItems(Alignment.CENTER);
        wrapper.setWidth("80%");
        if(lesson == null) {
            this.add(new Label("Sorry this lesson is not co completed yet"));
        }else {
            //meeting section
            VerticalLayout meetingSection = new VerticalLayout();
            meetingSection.getStyle().set("box-shadow", "0 4px 10px 0 rgba(0,0,0,0.2), 0 4px 20px 0 rgba(0,0,0,0.19)");
            Label meetingDescription = new Label("Запрошення до онлайн заняття");
            Button meetingButton = new Button("Перейти" ,event->{
                UI.getCurrent().getPage().executeJavaScript("window.open(\""+lesson.getMeetingLink()+"\", \"_blank\");");
            });

            meetingSection.add(meetingDescription,meetingButton);
            wrapper.add(meetingSection);
            //materials section
            VerticalLayout materials = new VerticalLayout();
            materials.getStyle().set("box-shadow", "0 4px 10px 0 rgba(0,0,0,0.2), 0 4px 20px 0 rgba(0,0,0,0.19)");
            Label materialsDescription = new Label("Заватажте матеріали для уроку тут");
            Button downloadButton = new Button("Завантажити", event->{
                UI.getCurrent().getPage().executeJavaScript("window.open(\""+lesson.getFilesLink()+"\", \"_blank\");");
            });
            materials.add(materialsDescription,downloadButton);
            wrapper.add(materials);
            //grammar section
            VerticalLayout grammar = new VerticalLayout();
            grammar.getStyle().set("box-shadow", "0 4px 10px 0 rgba(0,0,0,0.2), 0 4px 20px 0 rgba(0,0,0,0.19)");
            Label grammarDescription = new Label("Завдання з граматики");
            Button seeGrammar = new Button("Ропочати завдання", event->{
                UI.getCurrent().navigate("lesson/grammar");
            });
            grammar.add(grammarDescription,seeGrammar);
            wrapper.add(grammar);
            //vocabulary
            VerticalLayout vocabulary = new VerticalLayout();
            vocabulary.getStyle().set("box-shadow", "0 4px 10px 0 rgba(0,0,0,0.2), 0 4px 20px 0 rgba(0,0,0,0.19)");
            Label vocabularyDescription = new Label("Завдання с словництва");
            Button seeVocabulary = new Button("Ропочати завдання", event->{
                UI.getCurrent().navigate("lesson/vocabulary");
            });
            vocabulary.add(vocabularyDescription,seeVocabulary);
            wrapper.add(vocabulary);
            //reading
            VerticalLayout reading = new VerticalLayout();
            reading.getStyle().set("box-shadow", "0 4px 10px 0 rgba(0,0,0,0.2), 0 4px 20px 0 rgba(0,0,0,0.19)");
            Label readingDescription = new Label("Завдання з читання");
            Button seeReading = new Button("Ропочати завдання", event->{
                UI.getCurrent().navigate("lesson/reading");
            });
            reading.add(readingDescription,seeReading);
            wrapper.add(reading);
            //listening
            VerticalLayout listening = new VerticalLayout();
            listening.getStyle().set("box-shadow", "0 4px 10px 0 rgba(0,0,0,0.2), 0 4px 20px 0 rgba(0,0,0,0.19)");
            Label listeningDescription = new Label("Завдання з аудіювання");
            Button seeListening = new Button("Ропочати завдання", event->{
                UI.getCurrent().navigate("lesson/listening");
            });
            listening.add(listeningDescription,seeListening);
            wrapper.add(listening);
            //listening
            VerticalLayout homework = new VerticalLayout();
            listening.getStyle().set("box-shadow", "0 4px 10px 0 rgba(0,0,0,0.2), 0 4px 20px 0 rgba(0,0,0,0.19)");
            Label homeworkLabel = new Label("Домашньє завдання");
            Button seeHomework = new Button("Ропочати завдання", event->{
                UI.getCurrent().navigate("lesson/homework");
            });
            homework.add(homeworkLabel, seeHomework);
            wrapper.add(homework);
        }
        this.add(wrapper);
    }
}
