package de.atomfrede.mate.service.user;

import static org.fest.assertions.api.Assertions.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.atomfrede.mate.domain.entities.account.Account;
import de.atomfrede.mate.domain.entities.user.Role;
import de.atomfrede.mate.domain.entities.user.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:**/domain-context.xml" })
public class UserServiceTest {

	@Autowired
	UserService userService;

	@Before
	public void clear() throws UsernameAlreadyTakenException {
		List<User> allUsers = userService.findAll();
		
		for(User u:allUsers) {
			userService.remove(u);
		}
		
		userService.createUser("dummy", "dummy", "dummy", "dummy", "dummy");
	}
	
	@Test
	public void assertThatUserCanBePersisted() {
		User u = new User();
		u.setUsername("helloWorld");
		u.setAccount(new Account());
		userService.persist(u);
	}
	
	@Test
	public void assertThatCountReturnsCorrectValue() throws UsernameAlreadyTakenException {
		assertThat(userService.count()).isEqualTo(1L);
		userService.createUser("count", "of count", "count", "count", "count");
		assertThat(userService.count()).isEqualTo(2L);
	}
	
	@Test
	public void assertThatUserCanBeRetrievedByProperty() {
		assertThat(userService.findByProperty("username", "dummy")).isNotNull();
		assertThat(userService.findByProperty("username", "helloWorld")).isNull();
	}
	
	@Test(expected = UsernameAlreadyTakenException.class)
	public void assertThatNoDuplicateUserCanBeCreated() throws UsernameAlreadyTakenException {
		userService.createUser("dummy", "dummy", "dummy", "dummy", "dummy");
	}
	
	@Test
	public void assertThatAdminUserCanBeCreated() throws UsernameAlreadyTakenException {
		User u = userService.createAdminUser("admin", "admin", "admin", "admin", "admin");
		assertThat(u.getRole()).isEqualTo(Role.Admin);
	}
	
	@Test
	public void assertThatUserCanBeDeactivatedByUsername() throws UsernameAlreadyTakenException {
		userService.deactivateUserByName("dummy");
		assertThat(userService.getByUsername("dummy").isActive()).isFalse();
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void assertThatDeactivatingAnonExistingUserThrowsException() {
		userService.deactivateUserByName("noUser");
	}
	
	@Test
	public void assertThatCanCreateUserReturnsCorrectValues() {
		assertThat(userService.canCreateUser("dummy")).isFalse();
		assertThat(userService.canCreateUser("dummy-hello-world")).isTrue();
	}
}
