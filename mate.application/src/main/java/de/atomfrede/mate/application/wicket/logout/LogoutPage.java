package de.atomfrede.mate.application.wicket.logout;

import org.apache.wicket.Session;
import org.apache.wicket.markup.html.WebMarkupContainer;
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

	private static final long serialVersionUID = 2053750825891259558L;

	private static boolean loggedOut = false;
	private static int counter = 0;

	@SpringBean
	public UserService userService;

	WebMarkupContainer successContainer;

	public LogoutPage() {
		System.out.println("Counter = "+counter);
		UserAuthModel userModel = new UserAuthModel(User.class, -1L);

		successContainer = new WebMarkupContainer("logout-success");

		add(successContainer);
		
		successContainer.setVisible(false);
		
		add(new LoginPanel("loginPanel", userModel));

		if (!loggedOut) {
			loggedOut = true;
			doLogout();
		}
		
		
		
	}
	
	@Override
	public void onBeforeRender(){
		super.onBeforeRender();
		if(counter < 2){
			successContainer.setVisible(true);
		}else{
			successContainer.setVisible(false);
		}
		counter++;
	}

	public void doLogout() {
		getSession().invalidateNow();
		getSession().invalidate();
		UserSession session = (UserSession) Session.get();
		session.setUser(new UserAuthModel(User.class, -1L));
		loggedOut = true;
	}

	public static void reset() {
		loggedOut = false;
		counter = 0;
	}

}
