package de.atomfrede.mate.domain.entities.user;

import static org.fest.assertions.api.Assertions.assertThat;

import org.junit.Test;

import de.atomfrede.mate.domain.entities.account.Account;
import de.atomfrede.mate.domain.entities.consumption.Consumption;

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
		assertThat(u.isPassword("notMyPassword")).isFalse();
	}
	
	@Test
	public void assertThatPlainPasswordCanBeChecked() {
		User u = new User();
		u.setPassword("password");
		assertThat(u.isPasswordPlain("password")).isTrue();
		assertThat(u.isPasswordPlain("notMyPassword")).isFalse();
	}
	
	@Test
	public void assertThatConsumeWorksCorrectly() {
		User u = new User();
		u.setAccount(new Account());
		
		u.consume(new Consumption());
		
		assertThat(u.getAccount().getValue()).isLessThan(0.0);
		assertThat(u.getAccount().getValue()).isEqualTo(-1.0);
		assertThat(u.getConsumptions().size()).isEqualTo(1);
	}
}
