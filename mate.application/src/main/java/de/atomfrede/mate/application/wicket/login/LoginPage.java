package de.atomfrede.mate.application.wicket.login;

import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

import de.atomfrede.mate.application.wicket.base.AbstractBasePage;
import de.atomfrede.mate.application.wicket.security.UserAuthModel;
import de.atomfrede.mate.domain.entities.user.User;
import de.atomfrede.mate.service.user.UserService;
import de.atomfrede.mate.service.user.UsernameAlreadyTakenException;

@MountPath(value = "/", alt = "/login")
public class LoginPage extends AbstractBasePage {

	@SpringBean
	public UserService userService;

	public LoginPage() {
		addDummyUser();
		UserAuthModel userModel = new UserAuthModel(User.class, -1L);
		add(new LoginPanel("loginPanel", userModel));
	}
	
	private void addDummyUser(){
		try {
			userService.createUser("fred", "Frederik", "Hahne", "fred@mail.de", "fred");
		} catch (UsernameAlreadyTakenException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
