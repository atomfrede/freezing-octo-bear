package de.atomfrede.mate.service.consumption;

import static org.fest.assertions.api.Assertions.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.atomfrede.mate.domain.dao.account.AccountDao;
import de.atomfrede.mate.domain.entities.user.User;
import de.atomfrede.mate.service.bottle.BottleService;
import de.atomfrede.mate.service.user.UserService;
import de.atomfrede.mate.service.user.UsernameAlreadyTakenException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:**/domain-context-2.xml" })
public class ConsumptionServiceTest {

	@Autowired
	ConsumptionService consumptionService;

	@Autowired
	UserService userService;

	@Autowired
	BottleService bottleService;

	@Autowired
	AccountDao accountDao;

	User dummy;

	@Before
	public void setup() {
		try {
			dummy = userService.createUser("dummy-consume", "Test", "Dummy",
					"dummy@example.com", "password");
			bottleService.newCrate(dummy);
		} catch (UsernameAlreadyTakenException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@After
	public void after() {
		userService.deleteUser("dummy-consume");
	}

	@Test
	public void assertThatConsumeSetsAllValues() {
		consumptionService.consumeBottle(dummy);

		dummy = userService.getByUsername("dummy-consume");
		assertThat(dummy.getAccount().getValue()).isEqualTo(19.0);
		assertThat(bottleService.getNumberOfNotConsumedBottles()).isEqualTo(19);

		assertThat(consumptionService.getConsumedBottles(dummy)).isEqualTo(1);
	}
}
