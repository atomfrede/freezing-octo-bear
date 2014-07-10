package de.atomfrede.mate.application.mail;

import static org.fest.assertions.api.Assertions.*;

import org.apache.commons.configuration.ConfigurationException;
import org.junit.Test;

public class MailConfigTest {

	@Test
	public void assertThatPropertiesCanBeCreated() throws ConfigurationException {
		MailConfig config = new MailConfig();
		
		assertThat(config.from()).isEqualTo("alert@matetracker.de");
		
	}
}
