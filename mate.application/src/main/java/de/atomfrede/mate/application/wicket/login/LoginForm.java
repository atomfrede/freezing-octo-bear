package de.atomfrede.mate.application.wicket.login;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.link.Link;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import de.atomfrede.mate.application.wicket.base.AbstractBaseForm;
import de.atomfrede.mate.application.wicket.logout.LogoutPage;
import de.atomfrede.mate.application.wicket.register.RegisterPage;
import de.atomfrede.mate.application.wicket.security.UserAuthModel;
import de.atomfrede.mate.domain.entities.user.Role;
import de.atomfrede.mate.domain.entities.user.User;
import de.atomfrede.mate.service.user.UserService;

/**
 * A form component that provides login functionality for users.
 * 
 * It also handles errors nicely with appropriate feedback for the user.
 * 
 * @author fred
 * 
 */
@SuppressWarnings("serial")
public class LoginForm extends AbstractBaseForm<User> {

	@SpringBean
	UserService userService;

	TextField<String> usernameTextField;
	PasswordTextField passwordTextField;
	FeedbackPanel feedbackPanel;
	WebMarkupContainer usernameContainer, passwordContainer;
	
	Link<Void> registerLink;

	public LoginForm(String id, UserAuthModel model) {
		super(id, new CompoundPropertyModel<User>(model));

		feedbackPanel = new FeedbackPanel("feedbackPanel");
		add(feedbackPanel);
		feedbackPanel.setVisible(false);

		usernameContainer = new WebMarkupContainer("username_container");
		feedbackPanel.add(usernameContainer);
		add(usernameContainer);

		usernameTextField = new RequiredTextField<String>("username");
		usernameContainer.add(usernameTextField);

		passwordContainer = new WebMarkupContainer("password_container");
		feedbackPanel.add(passwordContainer);
		add(passwordContainer);

		passwordTextField = new PasswordTextField("password");
		passwordContainer.add(passwordTextField);
		
		registerLink = new Link<Void>("register-link") {

			@Override
			public void onClick() {
				setResponsePage(RegisterPage.class);
			}
		};
		
		add(registerLink);
	}

	@Override
	protected void onBeforeRender() {
		if (!hasError()) {
			// If the form is reloaded via F5 or so we should remove all stuff
			// indicating an error if no error is present anymore
			this.feedbackPanel.setVisible(false);
			this.usernameContainer.add(new AttributeModifier("class",
					"control-group"));
			this.passwordContainer.add(new AttributeModifier("class",
					"control-group"));
		}
		super.onBeforeRender();
	}

	@Override
	protected void onError() {
		// Only on validation errors we make the feedbackpanel visible
		this.feedbackPanel.setVisible(true);
		this.usernameContainer.add(new AttributeAppender("class", " error"));
		this.passwordContainer.add(new AttributeAppender("class", " error"));
	}

	@Override
	public void onSubmit() {
		String username = getModelObject().getUsername();
		String password = getModelObject().getPassword();

		User user = userService.getByUsername(username);

		// Check first if the user is not null
		if (user == null) {
			// Put a generic error message like user not found or password
			// incorrect
			error("Benutzer nicht gefunden oder Password nicht korrekt.");
			return;
		} else {
			
			if(user.isActive() || user.getRole() == Role.Admin) {
				// Check if the password is correct for the user found by the
				// provided username
				if(!user.isPassword(password)){
					error("Benutzer nicht gefunden oder Password nicht korrekt.");
					return;
				}
			} else {
				error("Benutzer noch nicht freigeschaltet");
				return;
			}
			
		}
		getSession().setUser(new UserAuthModel(User.class, user.getId()));

		LogoutPage.reset();
		
		setResponsePage(getApp().getHomePage());
		return;

	}

}
