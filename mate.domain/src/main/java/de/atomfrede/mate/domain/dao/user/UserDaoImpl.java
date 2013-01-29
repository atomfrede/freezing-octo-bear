package de.atomfrede.mate.domain.dao.user;

import java.util.List;

import javax.persistence.EntityManager;

import de.atomfrede.mate.domain.entities.user.User;

public class UserDaoImpl implements UserDao{

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
	public User findById(Long id) {
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public User merge(User entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public EntityManager getEntityManager() {
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
