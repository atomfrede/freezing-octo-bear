package de.atomfrede.mate.service.email;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class MailConfig {

	public static final String smtHost = "smtp.host";
	public static final String smtpPort = "smtp.port";
	public static final String smtpUSer = "smtp.user";
	public static final String smtpPassword = "smtp.password";
	public static final String smtpSSL = "smtp.ssl";
	public static final String smtpFrom = "smtp.from";
	
	private Configuration config;
	
	public MailConfig() throws ConfigurationException {
		this("mail.properties");
	}
	
	public MailConfig(String filename) throws ConfigurationException {
		config = new PropertiesConfiguration("mail.properties");
	}
	
	public String host() {
		return config.getString(smtHost);
	}
	
	public int port() {
		return config.getInt(smtpPort);
	}
	
	public String user() {
		return config.getString(smtpUSer);
	}
	
	public String password() {
		return config.getString(smtpPassword);
	}
	
	public boolean ssl() {
		return config.getBoolean(smtpSSL);
	}
	
	public String from() {
		return config.getString(smtpFrom);
	}

}
