package de.atomfrede.mate.service.user;

import de.atomfrede.mate.domain.entities.user.User;

public interface UserService {

	public User getByUsername(String username);
}
