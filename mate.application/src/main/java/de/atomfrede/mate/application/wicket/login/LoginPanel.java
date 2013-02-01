package de.atomfrede.mate.application.wicket.login;

import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.Panel;

import de.atomfrede.mate.application.wicket.security.UserAuthModel;

public class LoginPanel extends Panel {

	FeedbackPanel feedbackPanel;
	
	public LoginPanel(String id, UserAuthModel user) {
		super(id);
		LoginForm loginForm = new LoginForm("loginForm", user);
		add(loginForm);
	}

}
