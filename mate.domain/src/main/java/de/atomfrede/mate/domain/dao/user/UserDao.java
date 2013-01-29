package de.atomfrede.mate.domain.dao.user;

import de.atomfrede.mate.domain.dao.DAO;
import de.atomfrede.mate.domain.entities.user.User;

public interface UserDao extends DAO<User> {

	User getByUserName(String userName);
}
