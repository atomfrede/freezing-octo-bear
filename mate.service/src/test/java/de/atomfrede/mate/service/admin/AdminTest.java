package de.atomfrede.mate.service.admin;

import static org.fest.assertions.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.atomfrede.mate.domain.entities.user.User;
import de.atomfrede.mate.service.bottle.BottleService;
import de.atomfrede.mate.service.consumption.ConsumptionService;
import de.atomfrede.mate.service.user.UserService;
import de.atomfrede.mate.service.user.UsernameAlreadyTakenException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:**/domain-context-2.xml" })
public class AdminTest {

	@Autowired
	ConsumptionService consumptionService;
	
	@Autowired
	AdminService adminService;
	
	@Autowired
	UserService userService;

	@Autowired
	BottleService bottleService;
	
	@Test
	public void assertThatAllDataIsCleared() throws UsernameAlreadyTakenException {
		adminService.clearAllData();
		
		double largestAccountValue = 0.0;
		for(User u:userService.findAll()) {
			if(u.getAccount().getValue() > largestAccountValue) {
				largestAccountValue = u.getAccount().getValue();
			}
		}
		assertThat(largestAccountValue).isEqualTo(0.0);
	}
}
