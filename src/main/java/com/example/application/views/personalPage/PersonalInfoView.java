package com.example.application.views.personalPage;

import com.example.application.domain.Role;
import com.example.application.domain.User;
import com.example.application.service.AuthService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;

public class PersonalInfoView extends VerticalLayout {
    public PersonalInfoView(User user, AuthService authService){
        VerticalLayout innerLayout = new VerticalLayout();
        innerLayout.setMargin(true);
        innerLayout.setWidth("100%");
        innerLayout.getStyle().set("box-shadow", "0 4px 10px 0 rgba(0,0,0,0.2), 0 4px 20px 0 rgba(0,0,0,0.19)");
        Label usernameLabel = new Label("Ідентифікатор "+user.getUsername());
        Label renameLabel = new Label("Ім'я "+ user.getRealN());
        Label surnameLabel = new Label("Прізвище "+user.getSurname());
        Button logoutButton = new Button("Вийти з акаунту" , event -> {
            try {
                authService.logOut(user.getUsername());
            } catch (Exception e) {
                Notification.show("Неправильний логін або пароль");
            }
        });
        //add elements to the frame
        innerLayout.add(usernameLabel, renameLabel, surnameLabel, logoutButton);
        //create Admin button if user is admin
        if (user.getRoles().contains(Role.ADMIN)){
            innerLayout.add(new Button("Admin", event->{
                UI.getCurrent().navigate("admin");
            }));
        }
        add(innerLayout);
        this.setWidth("30%");
        UI.getCurrent().getPage().retrieveExtendedClientDetails(receiver -> {
            int screenWidth = receiver.getScreenWidth();
            if (screenWidth < 500) {
                this.setWidth("100%");
            }
            }
        );

    }
}
