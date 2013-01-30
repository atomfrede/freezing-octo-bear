package de.atomfrede.mate.domain;

import java.io.Serializable;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EntityLoaderDao {

	@Autowired
	SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public <T> T load(Class<T> clazz, Serializable id) {
		return (T) sessionFactory.getCurrentSession().load(clazz, id);
	}
}