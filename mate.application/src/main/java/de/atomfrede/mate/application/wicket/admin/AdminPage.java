package de.atomfrede.mate.application.wicket.admin;

import de.atomfrede.mate.application.wicket.BasePage;

public class AdminPage extends BasePage<Void> {

	public AdminPage(){
		super();
		add(new ClearAllDataPanel("clearAllData"));
	}
}
