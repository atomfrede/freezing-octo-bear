package de.atomfrede.mate.domain.entities.account;

import static org.fest.assertions.api.Assertions.assertThat;

import org.junit.Test;

public class AccountTest {

	@Test
	public void assertThatValueIsIncreased() {
		Account a = new Account();
		double before = a.getValue();
		a.increaseBy(10.0);
		
		assertThat(a.getValue()).isEqualTo(before + 10.0);
	}
	
	@Test
	public void assertThatNewAccountHasNoId() {
		Account a = new Account();
		
		assertThat(a.getId()).isNull();
	}
}
