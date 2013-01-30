package de.atomfrede.mate.domain.dao.user;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import de.atomfrede.mate.domain.dao.AbstractDAO;
import de.atomfrede.mate.domain.entities.user.User;

@Repository(value = "userDao")
public class UserDaoImpl extends AbstractDAO<User> implements UserDao {

	@Override
	public List<User> list(long offset, long count) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub

		return null;
	}

	@Override
	public User findByProperty(String propertyName, Object propertyValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(User entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeSafely(User entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void persist(User entity) {
		super.persist(entity);
	}

	@Override
	public User merge(User entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Class<User> getClazz() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getReference(User entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getByUserName(String userName) {
		// TODO Auto-generated method stub
		return null;
	}
}
