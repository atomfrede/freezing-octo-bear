package de.atomfrede.mate.service.user;

import static org.fest.assertions.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.atomfrede.mate.domain.entities.user.Role;
import de.atomfrede.mate.domain.entities.user.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:**/domain-context.xml" })
public class UserServiceTest {

	@Autowired
	UserService userService;

	@Test(expected = UsernameAlreadyTakenException.class)
	public void assertThatNoDuplicateUserCanBeCreated() throws UsernameAlreadyTakenException {
		userService.createUser("dummy", "dummy", "dummy", "dummy", "dummy");
		userService.createUser("dummy", "dummy", "dummy", "dummy", "dummy");
	}
	
	@Test
	public void assertThatAdminUserCanBeCreated() throws UsernameAlreadyTakenException {
		User u = userService.createAdminUser("admin", "admin", "admin", "admin", "admin");
		assertThat(u.getRole()).isEqualTo(Role.Admin);
	}
	
	@Test
	public void assertThatUserCanBeDeactivatedByUsername() {
		userService.deactivateUserByName("dummy");
		assertThat(userService.getByUsername("dummy").isActive()).isFalse();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void assertThatDeactivatingAnonExistingUserThrowsException() {
		userService.deactivateUserByName("noUser");
	}
}
