package com.example.application.views.login;

import com.example.application.service.AuthService;
import com.example.application.views.navbar.NavbarView;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

@Route(value = "login")
@RouteAlias(value = "")
@PageTitle("Login")
public class LoginView extends Div {
	public static final String ROUTE = "login";

	public LoginView(AuthService authService) {
		//block to center the form
		VerticalLayout centered = new VerticalLayout();
		centered.setAlignItems(FlexComponent.Alignment.CENTER);
		centered.setWidth("100%");
		//form
		VerticalLayout  verticalLayout = new VerticalLayout();
		verticalLayout.setWidth("auto");
		verticalLayout.setPadding(true);
		NavbarView navbarView = new NavbarView();
		TextField userNameTextField = new TextField("Ідентифікатор");
		PasswordField passwordField = new PasswordField("Пароль");
		passwordField.addKeyPressListener(Key.ENTER, event-> {
			boolean isLoggedIn = authService.authenticate(userNameTextField.getValue(), passwordField.getValue());
			if (isLoggedIn) UI.getCurrent().navigate("personal")
					;
			else Notification.show("Неправельний пароль або логін")
					;
		});
		verticalLayout.add(userNameTextField, passwordField,
				new Button("Увійти" , event -> {
					boolean isLoggedIn = authService.authenticate(userNameTextField.getValue(), passwordField.getValue());
					if (isLoggedIn) UI.getCurrent().navigate("personal")
							;
					else Notification.show("Неправельний пароль або логін")
							;
				})
				);
		centered.add(verticalLayout);
		add(navbarView,centered);
	}
}