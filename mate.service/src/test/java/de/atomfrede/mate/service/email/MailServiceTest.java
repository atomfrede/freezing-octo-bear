package de.atomfrede.mate.service.email;

import static org.fest.assertions.api.Assertions.*;

import org.apache.commons.mail.Email;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:**/domain-context-2.xml" })
public class MailServiceTest {

	@Autowired
	MailService mailService;
	
	@Test
	public void assertThatNoMatesMailCanBeGenerated() {
		Email mail = mailService.sendNoMatesMail();
		assertThat(mail.getSubject()).isEqualTo("No Mates left, oh Dear!");
	}
	
	@Test
	public void assertThatWarningMailCanBeGenerated() {
		Email mail = mailService.sendSupplyMail(3);
		assertThat(mail.getSubject()).isEqualTo("There are 3 left.");
	}
}
