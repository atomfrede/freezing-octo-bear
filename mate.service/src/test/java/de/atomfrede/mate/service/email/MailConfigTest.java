package de.atomfrede.mate.service.email;

import static org.fest.assertions.api.Assertions.*;

import org.apache.commons.configuration.ConfigurationException;
import org.junit.Test;

import de.atomfrede.mate.service.email.MailConfig;

public class MailConfigTest {

	@Test
	public void assertThatPropertiesCanBeCreated() throws ConfigurationException {
		MailConfig config = new MailConfig();
		
		assertThat(config.from()).isEqualTo("alert@matetracker.de");
		
	}
}
