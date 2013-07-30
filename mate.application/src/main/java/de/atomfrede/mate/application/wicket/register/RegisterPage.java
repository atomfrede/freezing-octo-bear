package de.atomfrede.mate.application.wicket.register;

import org.wicketstuff.annotation.mount.MountPath;

import de.atomfrede.mate.application.wicket.base.AbstractBasePage;

@SuppressWarnings("serial")
@MountPath(value = "/register")
public class RegisterPage extends AbstractBasePage{

	public RegisterPage() {
		add(new RegisterPanel("registerPanel"));
	}
}
