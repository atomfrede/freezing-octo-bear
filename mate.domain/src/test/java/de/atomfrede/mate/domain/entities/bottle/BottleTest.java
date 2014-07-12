package de.atomfrede.mate.domain.entities.bottle;

import static org.fest.assertions.api.Assertions.assertThat;

import org.junit.Test;

public class BottleTest {

	@Test
	public void assertThatGetAndSetWorkCorrectly() {
		Bottle b = new Bottle();
		b.consume();
		
		assertThat(b.isConsumed()).isTrue();
		assertThat(b.getId()).isNull();
	}
}
