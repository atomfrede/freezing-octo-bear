package de.atomfrede.mate.application.wicket.logout;

import org.apache.wicket.Session;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

import de.atomfrede.mate.application.wicket.base.AbstractBasePage;
import de.atomfrede.mate.application.wicket.login.LoginPanel;
import de.atomfrede.mate.application.wicket.security.UserAuthModel;
import de.atomfrede.mate.application.wicket.security.UserSession;
import de.atomfrede.mate.domain.entities.user.User;
import de.atomfrede.mate.service.user.UserService;

@MountPath(value = "/", alt = "/logout")
public class LogoutPage extends AbstractBasePage {

	@SpringBean
	public UserService userService;

	public LogoutPage() {
		doLogout();
		UserAuthModel userModel = new UserAuthModel(User.class, -1L);
		add(new LoginPanel("loginPanel", userModel));
	}
	
	public void doLogout(){
		getSession().invalidateNow();
		UserSession session = (UserSession)Session.get();
		session.setUser(new UserAuthModel(User.class, -1L));
	}
	
	

}
