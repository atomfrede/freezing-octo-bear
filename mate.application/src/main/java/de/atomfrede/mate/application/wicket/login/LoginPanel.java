package de.atomfrede.mate.application.wicket.login;

import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.panel.Panel;

import de.atomfrede.mate.application.wicket.security.UserAuthModel;

public class LoginPanel extends Panel {

	public LoginPanel(String id, UserAuthModel user) {
		super(id);
//		System.out.println("New LoginPanel");
		LoginForm loginForm = new LoginForm("loginForm", user);
		loginForm.add(new RequiredTextField<String>("email"));
		loginForm.add(new PasswordTextField("password"));
//		System.out.println("LoginForm Created "+loginForm);
		add(loginForm);
	}

}
