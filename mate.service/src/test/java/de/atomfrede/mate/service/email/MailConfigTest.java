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
		assertThat(config.host()).isEqualTo("smtp.gmail.com");
		assertThat(config.password()).isEqualTo("dummyPassword");
		assertThat(config.port()).isEqualTo(465);
		assertThat(config.ssl()).isTrue();
		assertThat(config.user()).isEqualTo("dummyUser");
		
	}
}
