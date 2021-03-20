package com.example.application.views.navbar;

import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class NavbarView extends VerticalLayout {
    public NavbarView(){
        Label label = new Label("ENGLISH LAB");
        Label labelSlogan = new Label("English for everyone everywhere");
        label.getStyle().set("color" , "black");
        label.getStyle().set("font-size", "36px");
        this.setMinHeight("150px");
        this.setAlignItems(Alignment.CENTER);
        this.setPadding(true);
        this.setWidth("100%");
        add(label);
        add(labelSlogan);
    }
}
