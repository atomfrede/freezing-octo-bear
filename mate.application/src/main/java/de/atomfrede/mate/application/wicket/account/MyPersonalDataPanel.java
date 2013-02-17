package de.atomfrede.mate.application.wicket.account;

import org.apache.wicket.markup.html.panel.Panel;

import de.atomfrede.mate.application.wicket.model.AbstractEntityModel;
import de.atomfrede.mate.domain.entities.user.User;

@SuppressWarnings("serial")
public class MyPersonalDataPanel extends Panel {



	public MyPersonalDataPanel(String id, AbstractEntityModel<User> model) {
		super(id, model);
		add(new MyPersonalDataForm("personalDataForm", model));
		
		
	}

}
