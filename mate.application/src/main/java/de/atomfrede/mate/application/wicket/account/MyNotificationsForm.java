package de.atomfrede.mate.application.wicket.account;

import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import de.atomfrede.mate.application.wicket.base.AbstractBaseForm;
import de.atomfrede.mate.application.wicket.model.AbstractEntityModel;
import de.atomfrede.mate.domain.entities.user.User;
import de.atomfrede.mate.service.user.UserService;


public class MyNotificationsForm extends AbstractBaseForm<User> {

	@SpringBean
	UserService userService;
	
	CheckBox mailNotifications, mobileNotifications;
	Boolean isMailnotification, isMobileNotification;
	
	public MyNotificationsForm(String id, AbstractEntityModel<User> model) {
		super(id, new CompoundPropertyModel<User>(model));
		
		isMailnotification = model.getObject().isMailNotification();
		isMobileNotification = model.getObject().isMobileNotification();
		
		mailNotifications = new CheckBox("check-mail", new PropertyModel<Boolean>(this, "isMailnotification"));
		mobileNotifications = new CheckBox("check-push", new PropertyModel<Boolean>(this, "isMobileNotification"));
		
		add(mailNotifications);
		add(mobileNotifications);
	
	}
	
	@Override
	public void onSubmit(){
		getModelObject().setMailNotification(isMailnotification);
		getModelObject().setMobileNotification(isMobileNotification);
		
		userService.persist(getModelObject());
	}
}
