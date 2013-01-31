package de.atomfrede.mate.application.wicket.login;

import org.apache.wicket.AttributeModifier;
import org.apache.wicket.behavior.AttributeAppender;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.form.PasswordTextField;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.spring.injection.annot.SpringBean;

import de.atomfrede.mate.application.wicket.base.AbstractBaseForm;
import de.atomfrede.mate.application.wicket.security.UserAuthModel;
import de.atomfrede.mate.domain.entities.user.User;
import de.atomfrede.mate.service.user.UserService;

public class LoginForm extends AbstractBaseForm<User> {

	@SpringBean
	UserService userService;

	TextField<String> usernameTextField;
	PasswordTextField passwordTextField;
	FeedbackPanel feedbackPanel;
	WebMarkupContainer usernameContainer, passwordContainer;
	
	
	
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
	}

	protected void setFeedbackPanel(FeedbackPanel feedback){
		this.feedbackPanel = feedback;
	}
	
	protected void onBeforeRender(){
		if(!hasError()){
			this.feedbackPanel.setVisible(false);
			this.usernameContainer.add(new AttributeModifier("class", "control-group"));
			this.passwordContainer.add(new AttributeModifier("class", "control-group"));
		}
		super.onBeforeRender();
	}
	@Override
	protected void onError(){
		System.out.println("OnError!");
		this.feedbackPanel.setVisible(true);
		
		this.usernameContainer.add(new AttributeAppender("class", " warning"));
		this.passwordContainer.add(new AttributeAppender("class", " warning"));
	}
	
	@Override
	public void onSubmit() {
		String username = getModelObject().getUsername();
		String email = getModelObject().getPassword();

		User user = userService.getByUsername(email);
		
		//Check first if the user is not null
		if(user == null){
			//Put a generic error message like user not found or password incorrect
			return;
		}else {
			//Check if the passwort is correct for the user found by the provided username
		}
		//
		Object ses = getSession();
		getSession().setUser(new UserAuthModel(User.class, user.getId()));
		
		setResponsePage(getApp().getHomePage());
		return;
		
		// String userName = getModelObject().getUsername();
		// String userName = getModelObject().getObject().getUsername();
		// String password = getModelObject().getObject().getPassword();

		// User user = userService.getByUserName(userName);
		//
		// if(user != null){
		//
		// }

		// getModelObject().getObject()
		// UserMode user =
	}

}
