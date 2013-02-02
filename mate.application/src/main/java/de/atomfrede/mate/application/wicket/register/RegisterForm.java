package de.atomfrede.mate.application.wicket.register;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;
import org.apache.wicket.validation.validator.EmailAddressValidator;

import de.atomfrede.mate.application.wicket.base.AbstractBaseForm;
import de.atomfrede.mate.application.wicket.logout.LogoutPage;
import de.atomfrede.mate.application.wicket.security.UserAuthModel;
import de.atomfrede.mate.domain.entities.user.AnonymousUser;
import de.atomfrede.mate.domain.entities.user.User;
import de.atomfrede.mate.service.user.UserService;
import de.atomfrede.mate.service.user.UsernameAlreadyTakenException;

@SuppressWarnings("serial")
public class RegisterForm extends AbstractBaseForm<User> {

	@SpringBean
	UserService userService;

	TextField<String> usernameTextField, emailTextField;
	PasswordTextField passwordTextField, passwordTextField2;
	FeedbackPanel feedbackPanel;
	WebMarkupContainer usernameContainer, emailContainer, passwordContainer,
			passwordContainer2;

	public RegisterForm(String id, UserAuthModel model) {
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

		passwordContainer2 = new WebMarkupContainer("password_container_2");
		feedbackPanel.add(passwordContainer2);
		add(passwordContainer2);

		passwordTextField2 = new PasswordTextField("password_2");
		passwordContainer2.add(passwordTextField2);

		emailContainer = new WebMarkupContainer("email_container");
		feedbackPanel.add(emailContainer);
		add(emailContainer);

		emailTextField = new RequiredTextField<String>("email");
		emailTextField.add(EmailAddressValidator.getInstance());
		emailContainer.add(emailTextField);

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
			this.passwordContainer2.add(new AttributeModifier("class",
					"control-group"));
			this.emailContainer.add(new AttributeModifier("class",
					"control-group"));
		}
		super.onBeforeRender();
	}

	@Override
	protected void onError() {
		// Only on validation errors we make the feedbackpanel visible
		this.feedbackPanel.setVisible(true);
		if (usernameTextField.hasErrorMessage()) {
			this.usernameContainer
					.add(new AttributeAppender("class", " error"));
		} else {
			this.usernameContainer.add(new AttributeModifier("class",
					"control-group"));
		}

		if (passwordTextField.hasErrorMessage()) {
			this.passwordContainer
					.add(new AttributeAppender("class", " error"));
		} else {
			this.passwordContainer.add(new AttributeModifier("class",
					"control-group"));
		}

		if (passwordTextField2.hasErrorMessage()) {
			this.passwordContainer2
					.add(new AttributeAppender("class", " error"));
		} else {
			this.passwordContainer2.add(new AttributeModifier("class",
					"control-group"));
		}

		if (emailTextField.hasErrorMessage()) {
			this.emailContainer.add(new AttributeAppender("class", " error"));
		} else {
			this.emailContainer.add(new AttributeModifier("class",
					"control-group"));
		}

	}

	@Override
	public void onSubmit() {
		String username = getModel().getObject().getUsername();
		String password1 = getModel().getObject().getPassword();
		String password2 = "";
		if (getModel().getObject() instanceof AnonymousUser) {
			password2 = ((AnonymousUser) getModel().getObject())
					.getPassword_2();
		}
		String email = getModel().getObject().getEmail();

		boolean passwordError = false;
		boolean usernameError = false;
		// First check if the passwords are equal
		if (!password1.equals(User.cryptPass(password2))) {
			// Error Message here
			error("Passwörter stimmen nicht überein!");
			passwordError = true;

		}

		// Then check if the user name is not taken already
		if (!userService.canCreateUser(username)) {
			// Error Message here
			error("Benutzername bereist vergeben.");
			usernameError = true;
		}
		
		if(passwordError || usernameError){
			return;
		}

		try {
			User user = userService.createUser(username, "", "", email,
					password2);

			getSession().setUser(new UserAuthModel(User.class, user.getId()));

			LogoutPage.reset();

			setResponsePage(getApp().getHomePage());
			return;

		} catch (UsernameAlreadyTakenException e) {
			// Very unlikely to occur here because we have checked it before
		}

		return;

	}

}
