package de.atomfrede.mate.application.wicket.user;

import org.wicketstuff.annotation.mount.MountPath;

import de.atomfrede.mate.application.wicket.BasePage;


@SuppressWarnings("serial")
@MountPath(value = "/users", alt = "/users")
public class UserPage extends BasePage<Void> {

	public UserPage() {
		super();
		add(new UserListPanel("users"));
	}
}
