package de.atomfrede.mate.application.wicket.account;

import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.spring.injection.annot.SpringBean;

import de.atomfrede.mate.application.wicket.base.AbstractBaseForm;
import de.atomfrede.mate.application.wicket.model.AbstractEntityModel;
import de.atomfrede.mate.domain.entities.user.User;
import de.atomfrede.mate.service.user.UserService;

@SuppressWarnings("serial")
public class MyPersonalDataForm extends AbstractBaseForm<User> {

	@SpringBean
	UserService userService;
	
	TextField<String> firstname, lastname, mail;

	String cFirstname, cLastname, cMail;
	
	public MyPersonalDataForm(String id, AbstractEntityModel<User> model) {
		super(id, model);

		cFirstname = model.getObject().getFirstname();
		cLastname = model.getObject().getLastname();
		cMail = model.getObject().getEmail();
		
		firstname = new TextField<>("firstname", new PropertyModel<String>(this, "cFirstname"));
		lastname = new TextField<>("lastname", new PropertyModel<String>(this, "cLastname"));
		mail = new TextField<>("mail", new PropertyModel<String>(this, "cMail"));
		
		add(firstname);
		add(lastname);
		add(mail);
	}
	
	@Override
	public void onSubmit(){
		getModelObject().setFirstname(cFirstname);
		getModelObject().setLastname(cLastname);
		getModelObject().setEmail(cMail);
		
		userService.persist(getModelObject());
	}

}
