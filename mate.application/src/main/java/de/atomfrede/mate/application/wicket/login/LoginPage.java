package de.atomfrede.mate.application.wicket.login;

import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

import de.atomfrede.mate.application.wicket.base.AbstractBasePage;
import de.atomfrede.mate.application.wicket.security.UserAuthModel;
import de.atomfrede.mate.domain.entities.user.User;
import de.atomfrede.mate.service.user.UserService;

@MountPath(value = "/", alt = "/login")
public class LoginPage extends AbstractBasePage {

	@SpringBean
	public UserService userService;

	public LoginPage() {
		UserAuthModel userModel = new UserAuthModel(User.class, -1L);
		add(new LoginPanel("loginPanel", userModel));
	}

}
