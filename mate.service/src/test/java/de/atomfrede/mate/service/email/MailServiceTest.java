package de.atomfrede.mate.service.email;

import static org.fest.assertions.api.Assertions.assertThat;

import org.apache.commons.mail.Email;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.atomfrede.mate.domain.entities.user.User;
import de.atomfrede.mate.service.user.UserService;
import de.atomfrede.mate.service.user.UsernameAlreadyTakenException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:**/domain-context-2.xml" })
public class MailServiceTest {

	@Autowired
	MailService mailService;
	
	@Autowired
	UserService userService;
	
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
	
	@Test
	public void assertThatNoMatesMailCanBeGeneratedWithUsers() throws UsernameAlreadyTakenException {
		User u1 = userService.createUser("user1", "u", "1", "u1@matetracker.de", "u1");
		User u2 = userService.createUser("user2", "u", "2", "u2@matetracker.de", "u2");
		
		u1.setMailNotification(true);
		u2.setMailNotification(false);
		
		userService.persist(u1);
		userService.persist(u2);
		
		Email mail = mailService.sendNoMatesMail();
		assertThat(mail.getToAddresses().size()).isEqualTo(1);
		
		userService.deleteUser("user1");
		userService.deleteUser("user2");
		
	}
	
	@Test
	public void assertThatWarningMailCanBeGeneratedWithUsers() throws UsernameAlreadyTakenException {
		User u1 = userService.createUser("user1", "u", "1", "u1@matetracker.de", "u1");
		User u2 = userService.createUser("user2", "u", "2", "u2@matetracker.de", "u2");
		
		u1.setMailNotification(true);
		u2.setMailNotification(false);
		
		userService.persist(u1);
		userService.persist(u2);
		
		Email mail = mailService.sendSupplyMail(3);
		assertThat(mail.getToAddresses().size()).isEqualTo(1);
		
		userService.deleteUser("user1");
		userService.deleteUser("user2");
		
	}
}
