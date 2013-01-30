package de.atomfrede.mate.domain.dao;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import de.atomfrede.mate.domain.entities.AbstractEntity;
import de.atomfrede.mate.domain.entities.user.User;

@Repository
public abstract class AbstractDAO <EntityClass extends AbstractEntity> implements DAO<EntityClass>{

	@Resource
	protected SessionFactory sessionFactory;
	
	public Session getSession(){
		try{
			return sessionFactory.getCurrentSession();
		}catch(HibernateException he){
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


}
