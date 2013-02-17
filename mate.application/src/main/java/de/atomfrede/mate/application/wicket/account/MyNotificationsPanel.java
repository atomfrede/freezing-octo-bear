package de.atomfrede.mate.application.wicket.account;

import org.apache.wicket.markup.html.panel.Panel;

import de.atomfrede.mate.application.wicket.model.AbstractEntityModel;
import de.atomfrede.mate.domain.entities.user.User;

@SuppressWarnings("serial")
public class MyNotificationsPanel extends Panel {

	public MyNotificationsPanel(String id, AbstractEntityModel<User> model) {
		super(id, model);
		// TODO Auto-generated constructor stub
		add(new MyNotificationsForm("notificationForm", model));
	}
	
	
	

}
