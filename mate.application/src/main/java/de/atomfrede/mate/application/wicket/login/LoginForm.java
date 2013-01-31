package de.atomfrede.mate.application.wicket.login;

import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import de.atomfrede.mate.application.wicket.base.AbstractBaseForm;
import de.atomfrede.mate.application.wicket.security.UserAuthModel;
import de.atomfrede.mate.domain.entities.user.User;
import de.atomfrede.mate.service.user.UserService;

public class LoginForm extends AbstractBaseForm<User> {

//	@SpringBean
	UserService userService;

	public LoginForm(String id, UserAuthModel model) {
		super(id, new CompoundPropertyModel<User>(model));
	}

	@Override
	public void onSubmit() {
		String username = getModelObject().getUsername();
		String email = getModelObject().getEmail();
//		String password = getModelObject().get

		User user = userService.getByUsername(email);
		
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
	/*
	 * @Override public void onSubmit() { UserModel user = userDao.findByEmail(
	 * getModelObject().getEmail());
	 * 
	 * // check if user can login and dspring bean not foundo login if(user != null &&
	 * user.isPassword(getModelObject().getPassword()) &&
	 * user.hasRole("is_user")) {
	 * 
	 * // login user getSession().setUser(user);
	 * 
	 * // goto home page setResponsePage(getApp().getHomePage()); return; }
	 * 
	 * transError("Wrong user data."); }
	 */

}
