package de.atomfrede.mate.service.email;

import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.atomfrede.mate.domain.entities.user.User;
import de.atomfrede.mate.service.user.UserService;

@Service(value = "mailService")
@Transactional(rollbackFor = Exception.class)
public class MailServiceImpl implements MailService {

	@Autowired
	UserService userService;

	@Override
	public void sendNoMatesMail() {
		List<User> allUsers = userService.findAll();
		try {

			Email email = new SimpleEmail();
			// email.setHostName("smtp.googlemail.com");
//			email.setSmtpPort(465);
//			email.setAuthenticator(new DefaultAuthenticator("username",
//					"password"));
			email.setSSLOnConnect(true);
			email.setFrom("supplies@matetracker.com");
			email.setSubject("No Mates left!");
			email.setMsg("There are no mates left. We need supplies *now*!");

			for (User user : allUsers) {
				String mail = user.getEmail();
				email.addTo(mail);
			}
			email.send();
		} catch (EmailException emailEx) {

		}

	}

	@Override
	public void sendSupplyMail(int availableMates) {
		List<User> allUsers = userService.findAll();
		try {

			Email email = new SimpleEmail();
			// email.setHostName("smtp.googlemail.com");
//			email.setSmtpPort(465);
//			email.setAuthenticator(new DefaultAuthenticator("username",
//					"password"));
			email.setSSLOnConnect(true);
			email.setFrom("supplies@matetracker.com");
			email.setSubject("There are "+availableMates+" left.");
			email.setMsg("Only "+availableMates+" left. You should resupply soon.");

			for (User user : allUsers) {
				String mail = user.getEmail();
				email.addTo(mail);
			}
			email.send();
		} catch (EmailException emailEx) {

		}

	}

}
