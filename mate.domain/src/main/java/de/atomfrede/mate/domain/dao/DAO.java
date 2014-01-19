package de.atomfrede.mate.domain.dao;

import java.util.List;

import de.atomfrede.mate.domain.entities.IEntity;

public interface DAO<EntityClass extends IEntity> {

	List<EntityClass> list(long offset, long count);
	
	List<EntityClass> list(long offset, long count, String orderProperty, boolean desc);

	List<EntityClass> findAll();

	EntityClass findById(Long id);

	EntityClass findByProperty(String propertyName, Object propertyValue);

	List<EntityClass> findAllByProperty(String propertName, Object propertyValue);
	
	void remove(EntityClass entity);
	
	void remove(Long id);

	void persist(EntityClass entity);

	long count();

	Class<EntityClass> getClazz();

}
