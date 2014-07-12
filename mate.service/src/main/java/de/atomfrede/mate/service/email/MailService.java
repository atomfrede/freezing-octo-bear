package de.atomfrede.mate.service.email;

import org.apache.commons.mail.Email;

public interface MailService {

	public Email sendNoMatesMail();
	
	public Email sendSupplyMail(int availableMates);
}
