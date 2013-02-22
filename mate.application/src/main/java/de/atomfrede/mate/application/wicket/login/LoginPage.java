package de.atomfrede.mate.application.wicket.login;

import org.apache.wicket.spring.injection.annot.SpringBean;
import org.wicketstuff.annotation.mount.MountPath;

import de.atomfrede.mate.application.wicket.base.AbstractBasePage;
import de.atomfrede.mate.application.wicket.security.UserAuthModel;
import de.atomfrede.mate.domain.entities.user.User;
import de.atomfrede.mate.service.bottle.BottleService;
import de.atomfrede.mate.service.consumption.ConsumptionService;
import de.atomfrede.mate.service.user.UserService;
import de.atomfrede.mate.service.user.UsernameAlreadyTakenException;

@MountPath(value = "/login", alt = "/login")
public class LoginPage extends AbstractBasePage {

	@SpringBean
	public UserService userService;
	
	@SpringBean
	public BottleService bottleService;
	
	@SpringBean
	public ConsumptionService consumptionService;

	public LoginPage() {
//		addDummyBottles();
//		addDummyUser();
		
		UserAuthModel userModel = new UserAuthModel(User.class, -1L);
		add(new LoginPanel("loginPanel", userModel));
	}
	
	private void addDummyUser(){
		try {
			User fred = userService.createUser("fred", "Frederik", "Hahne", "fred@mail.de", "fred");
			User max = userService.createUser("max", "Max", "Mustermann", "max@muster.de", "max");
			userService.createUser("max1", "Max1", "Mustermann", "max@muster.de", "max");
			userService.createUser("max2", "Max2", "Mustermann", "max@muster.de", "max");
			userService.createUser("max3", "Max2", "Mustermann", "max@muster.de", "max");
			
			consumptionService.consumeBottle(fred);
			consumptionService.consumeBottle(fred);
			consumptionService.consumeBottle(max);
		} catch (UsernameAlreadyTakenException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void addDummyBottles(){
		bottleService.newCrate();
		
	}

}
