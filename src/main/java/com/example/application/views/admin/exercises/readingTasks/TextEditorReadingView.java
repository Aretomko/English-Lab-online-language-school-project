package com.example.application.views.admin.exercises.readingTasks;

import com.example.application.domain.Reading;
import com.example.application.domain.User;
import com.example.application.service.ReadingService;
import com.example.application.views.admin.NavbarAdmin;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.server.VaadinSession;



public class TextEditorReadingView extends VerticalLayout {
    public TextEditorReadingView(ReadingService readingService){
        NavbarAdmin navbarAdmin = new NavbarAdmin();
        this.add(navbarAdmin);
        //find reading to attach text
        String readingId = VaadinSession.getCurrent().getAttribute("readingId").toString();
        Reading reading = readingService.findReadingById(readingId);
        VerticalLayout verticalLayout = new VerticalLayout();
        verticalLayout.setAlignItems(Alignment.CENTER);
        this.setWidth("100%");
        TextArea textArea = new TextArea();
        textArea.setWidth("80%");
        if(reading.getText()!=null) textArea.setValue(reading.getText())
                ;
        else textArea.setPlaceholder("no text yet")
                ;
        Button submit = new Button("Save", event->{
            reading.setText(textArea.getValue());
            readingService.save(reading);
            UI.getCurrent().navigate("admin/readingTasks");
        });
        verticalLayout.add(textArea, submit);
        this.add(verticalLayout);
    }
}
