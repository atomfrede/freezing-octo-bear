package de.atomfrede.mate.service.email;

import java.util.List;

import org.apache.commons.configuration.ConfigurationException;
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

	private Email createSimpleMail() throws ConfigurationException, EmailException {
		MailConfig config = new MailConfig();
		Email email = new SimpleEmail();
		email.setHostName(config.host());
		email.setSmtpPort(config.port());
		email.setAuthenticator(new DefaultAuthenticator(config.user(), config.password()));
		email.setSSLOnConnect(config.ssl());
		email.setFrom(config.from());
		
		return email;
	}
	
	@Override
	public Email sendNoMatesMail() {
		Email email = null;
		List<User> allUsers = userService.findAll();
		try {
			email = createSimpleMail();
			email.setSubject("No Mates left, oh Dear!");
			email.setMsg("There are no mates left. We need supplies *now*!");

			for (User user : allUsers) {
				if(user.isMailNotification()) {
					String mail = user.getEmail();
					email.addTo(mail);
				}
				
			}
			email.send();
			return email;
		} catch (EmailException | ConfigurationException emailEx) {
			return email;
		}

	}

	@Override
	public Email sendSupplyMail(int availableMates) {
		Email email = null;
		List<User> allUsers = userService.findAll();
		try {
			email = createSimpleMail();
			email.setSubject("There are "+availableMates+" left.");
			email.setMsg("We are running low on Mate. Only "+availableMates+" left. You should resupply soon.");

			for (User user : allUsers) {
				if(user.isMailNotification()) {
					String mail = user.getEmail();
					email.addTo(mail);
				}
				
			}
			email.send();
			return email;
		} catch (EmailException | ConfigurationException emailEx) {
			return email;
		}

	}

}
