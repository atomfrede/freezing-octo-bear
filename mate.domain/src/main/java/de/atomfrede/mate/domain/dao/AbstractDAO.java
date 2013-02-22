package de.atomfrede.mate.domain.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import de.atomfrede.mate.domain.entities.AbstractEntity;

@Component
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

	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public List<EntityClass> list(long offset, long count) {
		Session session = getSession();

		Criteria crit = session.createCriteria(getClazz());
		crit.setFirstResult((int)offset);
		crit.setMaxResults((int)count);

		return crit.list();
	}
	
	@Transactional
	public void persist(EntityClass entity) {
		getSession().saveOrUpdate(entity);
		getSession().flush();
	}
	
	@Transactional
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
		Criteria criteria = getSession().createCriteria(getClazz());
		List<EntityClass> entities = (List<EntityClass>) criteria.list();
		return entities;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly= true)
	public EntityClass findByProperty(String propertyName, Object propertyValue){
		Criteria crit = getSession().createCriteria(clazz);
		crit.add(Restrictions.eq(propertyName, propertyValue));
		return (EntityClass) crit.uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly= true)
	public List<EntityClass> findAllByProperty(String propertyName, Object propertyValue){
		Criteria crit = getSession().createCriteria(clazz);
		crit.add(Restrictions.eq(propertyName, propertyValue));
		return crit.list();
	}
	
	@Override
	public long count() {
		return ((Long) getSession().createQuery("select count(*) from "+clazz.getSimpleName())
				.uniqueResult()).intValue();
	}
	
	@Override
	public Class<EntityClass> getClazz(){
		return clazz;
	}
}
