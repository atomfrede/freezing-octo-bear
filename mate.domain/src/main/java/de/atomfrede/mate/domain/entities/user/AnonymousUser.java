package de.atomfrede.mate.domain.entities.user;

public class AnonymousUser extends User {

	protected String password_2;
	
	public AnonymousUser(){
	}

	public String getPassword_2() {
		return password_2;
	}

	public void setPassword_2(String password_2) {
		this.password_2 = password_2;
	}
	
	
}
