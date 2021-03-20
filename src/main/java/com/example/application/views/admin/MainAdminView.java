package com.example.application.views.admin;

import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.RouterLayout;

public class MainAdminView extends VerticalLayout implements RouterLayout {
    public MainAdminView(){
        NavbarAdmin navbarAdmin = new NavbarAdmin();
        VerticalLayout centered = new VerticalLayout();
        centered.setWidth("100%");
        centered.setAlignItems(Alignment.CENTER);
        Label loveLabel = new Label("I love you !!!");
        centered.add(loveLabel);
        add(navbarAdmin);
    }
}
