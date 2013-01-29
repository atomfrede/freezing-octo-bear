package de.atomfrede.mate.domain.dao;

import java.util.List;

import javax.persistence.EntityManager;

import de.atomfrede.mate.domain.entities.IEntity;

public interface DAO<EntityClass extends IEntity> {

	List<EntityClass> list(long offset, long count);
	
	List<EntityClass> findAll();

	EntityClass findById(Long id);

	EntityClass findByProperty(String propertyName, Object propertyValue);

	void remove(EntityClass entity);

	void removeSafely(EntityClass entity);

	void persist(EntityClass entity);

	EntityClass merge(EntityClass entity);

	EntityManager getEntityManager();

	long size();

	Class<EntityClass> getClazz();

	EntityClass getReference(EntityClass entity);
}
