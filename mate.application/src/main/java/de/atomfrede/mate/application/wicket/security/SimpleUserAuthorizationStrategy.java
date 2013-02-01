package de.atomfrede.mate.application.wicket.security;

import org.apache.wicket.Component;
import org.apache.wicket.Page;
import org.apache.wicket.RestartResponseAtInterceptPageException;
import org.apache.wicket.Session;
import org.apache.wicket.authorization.Action;
import org.apache.wicket.authorization.IAuthorizationStrategy;
import org.apache.wicket.request.component.IRequestableComponent;

import de.atomfrede.mate.application.wicket.login.LoginPage;
import de.atomfrede.mate.application.wicket.logout.LogoutPage;

public class SimpleUserAuthorizationStrategy implements IAuthorizationStrategy{

	@Override
	public <T extends IRequestableComponent> boolean isInstantiationAuthorized(
			Class<T> componentClass) {
		if(!Page.class.isAssignableFrom(componentClass)){
			return true;
		}
		
		if(LogoutPage.class.isAssignableFrom(componentClass)){
			return true;
		}
		
		if(LoginPage.class.isAssignableFrom(componentClass)){
			return true;
		}
		
		UserSession session = (UserSession)Session.get();
		System.out.println("User "+session.getUser().isAnonymous);
		if(session.getUser().isAnonymous){
			throw new RestartResponseAtInterceptPageException(LoginPage.class);
		}
		return true;
	}

	@Override
	public boolean isActionAuthorized(Component component, Action action) {
		return true;
	}

}
