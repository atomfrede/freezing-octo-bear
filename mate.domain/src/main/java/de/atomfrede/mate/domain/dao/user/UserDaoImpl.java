package de.atomfrede.mate.domain.dao.user;

import org.springframework.stereotype.Repository;

import de.atomfrede.mate.domain.dao.AbstractDAO;
import de.atomfrede.mate.domain.entities.user.User;

@Repository(value = "userDao")
public class UserDaoImpl extends AbstractDAO<User> implements UserDao {

	public UserDaoImpl() {
		super(User.class);
	}

	@Override
	public long count() {
		return ((Long) getSession().createQuery("select count(*) from User")
				.uniqueResult()).intValue();
	}
	
	@Override
	public User getByUserName(String userName) {
		// TODO Auto-generated method stub
		return null;
	}
}
