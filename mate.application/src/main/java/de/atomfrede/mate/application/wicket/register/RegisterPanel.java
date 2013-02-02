package de.atomfrede.mate.application.wicket.register;

import org.apache.wicket.markup.html.panel.Panel;

import de.atomfrede.mate.application.wicket.security.UserAuthModel;
import de.atomfrede.mate.domain.entities.user.User;

@SuppressWarnings("serial")
public class RegisterPanel extends Panel{

	public RegisterPanel(String id) {
		super(id);
		UserAuthModel user = new UserAuthModel(User.class, -1L);
		RegisterForm registerForm = new RegisterForm("registerForm", user);
		add(registerForm);
	}

}
