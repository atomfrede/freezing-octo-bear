package de.atomfrede.mate.service.user;

import de.atomfrede.mate.domain.entities.user.User;
import de.atomfrede.mate.service.EntityService;

public interface UserService extends EntityService<User> {

	public User getByUsername(String username);

	/**
	 * Creates a new user and tries to write it into database.
	 * 
	 * @param username
	 * @param firstname
	 * @param lastname
	 * @param email
	 * @param password
	 * @return
	 */
	public User createUser(String username, String firstname, String lastname,
			String email, String password) throws UsernameAlreadyTakenException;
	
	public boolean canCreateUser(String username);

}
