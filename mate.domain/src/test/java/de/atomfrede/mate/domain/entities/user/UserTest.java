package de.atomfrede.mate.domain.entities.user;

import static org.fest.assertions.api.Assertions.assertThat;

import org.junit.Test;

public class UserTest {

	@Test
	public void assertThatPasswordIsCrypted() {
		User u = new User();
		u.setPassword("password");
		assertThat(u.getPassword()).isNotEqualTo("password");
	}
	
	@Test
	public void assertThatPasswordCanBeChecked() {
		User u = new User();
		u.setPassword("password");
		assertThat(u.isPassword(u.getPassword())).isTrue();
	}
	
	@Test
	public void assertThatPlainPasswordCanBeChecked() {
		User u = new User();
		u.setPassword("password");
		assertThat(u.isPasswordPlain("password")).isTrue();
	}
}
