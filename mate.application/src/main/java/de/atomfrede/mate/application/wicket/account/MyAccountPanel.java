package de.atomfrede.mate.application.wicket.account;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.Panel;

import de.atomfrede.mate.application.wicket.model.AbstractEntityModel;
import de.atomfrede.mate.domain.entities.user.User;

public class MyAccountPanel extends Panel {

	Label myAccountValue, myAccountBottles;

	AbstractEntityModel<User> model;

	public MyAccountPanel(String id, AbstractEntityModel<User> model) {
		super(id, model);
		this.model = model;
		updateLabels();
	}

	@Override
	public void onBeforeRender(){
		super.onBeforeRender();
		updateLabels();
	}
	
	private void updateLabels() {
		double accountValue = model.getObject().getAccount().getValue();
		int consumedBottles = 0;
		
		if(model.getObject().getConsumptions() != null) {
			consumedBottles = model.getObject().getConsumptions().size();
		}

		if (myAccountValue != null) {
			remove(myAccountValue);
		}
		if (myAccountBottles != null) {
			remove(myAccountBottles);
		}

		myAccountValue = new Label("my-account-value", "Kontostand: "
				+ accountValue + "€");
		myAccountBottles = new Label("my-account-bottles",
				"Konsumierte Flaschen: " + consumedBottles);

		add(myAccountValue);
		add(myAccountBottles);
	}

}
