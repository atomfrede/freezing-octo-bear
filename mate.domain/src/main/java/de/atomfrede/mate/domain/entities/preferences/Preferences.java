package de.atomfrede.mate.domain.entities.preferences;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import de.atomfrede.mate.domain.entities.AbstractEntity;

@Entity
@Table(name = "preferences")
public class Preferences extends AbstractEntity{
	
	@GenericGenerator(name = "PreferenceIdGenerator", strategy = "org.hibernate.id.MultipleHiLoPerTableGenerator", parameters = {
			@Parameter(name = "table", value = "IdentityGenerator"),
			@Parameter(name = "primary_key_column", value = "sequence_name"),
			@Parameter(name = "primary_key_value", value = "Preferences"),
			@Parameter(name = "value_column", value = "next_hi_value"),
			@Parameter(name = "primary_key_length", value = "100"),
			@Parameter(name = "max_lo", value = "1000") })
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE, generator = "PreferenceIdGenerator")
	protected Long id;
	
	@Column(name = "smptserver")
	protected String smtpServer;
	
	@Column(name = "smptpassword")
	protected String smptPassword;
	
	@Column(name = "senderadress")
	protected String senderAdress;
	
	@Column(name = "sendername")
	protected String senderName;
	
	@Override
	public Long getId() {
		return id;
	}

	public String getSmtpServer() {
		return smtpServer;
	}

	public void setSmtpServer(String smtpServer) {
		this.smtpServer = smtpServer;
	}

	public String getSmptPassword() {
		return smptPassword;
	}

	public void setSmptPassword(String smptPassword) {
		this.smptPassword = smptPassword;
	}

	public String getSenderAdress() {
		return senderAdress;
	}

	public void setSenderAdress(String senderAdress) {
		this.senderAdress = senderAdress;
	}

	public String getSenderName() {
		return senderName;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}
}
