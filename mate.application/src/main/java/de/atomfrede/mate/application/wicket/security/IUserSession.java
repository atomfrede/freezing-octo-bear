package de.atomfrede.mate.application.wicket.security;

public interface IUserSession<T> {

	T getUser();

	void setUser(T user);
}
