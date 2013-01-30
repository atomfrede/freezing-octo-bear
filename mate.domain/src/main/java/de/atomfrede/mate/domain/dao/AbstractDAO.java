package de.atomfrede.mate.domain.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import de.atomfrede.mate.domain.entities.AbstractEntity;
import de.atomfrede.mate.domain.entities.user.User;

@Repository
public abstract class AbstractDAO<EntityClass extends AbstractEntity>
		implements DAO<EntityClass> {

	protected Class<EntityClass> clazz;

	public AbstractDAO(Class<EntityClass> clazz){
		this.clazz = clazz;
	}
	
	@Resource
	protected SessionFactory sessionFactory;

	public Session getSession() {
		try {
			return sessionFactory.getCurrentSession();
		} catch (HibernateException he) {
			return sessionFactory.openSession();
		}
	}

	public void persist(EntityClass entity) {
		getSession().saveOrUpdate(entity);
		getSession().flush();
	}

	public void remove(EntityClass entity) {
		getSession().delete(entity);
		getSession().flush();
	}

	@SuppressWarnings("unchecked")
	@Override
	public EntityClass findById(Long id) {
		return (EntityClass) getSession().get(getClazz(), id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EntityClass> findAll() {
		System.out.println("getSession "+getSession());
		System.out.println("Clazz "+getClazz());
		Criteria criteria = getSession().createCriteria(getClazz());
		List<EntityClass> entities = (List<EntityClass>) criteria.list();
		return entities;
	}
	
	@Override
	public Class<EntityClass> getClazz(){
		return clazz;
	}
}
