package de.atomfrede.mate.service.email;

public interface MailService {

	public void sendNoMatesMail();
	
	public void sendSupplyMail(int availableMates);
}
