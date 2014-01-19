package de.atomfrede.mate.service;

import java.util.List;

import de.atomfrede.mate.domain.entities.IEntity;

public interface EntityService<EntityClass extends IEntity> {

	List<EntityClass> list(long offset, long count);
	
	List<EntityClass> list(long offset, long count, String orderProperty, boolean desc);

	List<EntityClass> findAll();

	EntityClass findById(Long id);

	EntityClass findByProperty(String propertyName, Object propertyValue);

	void remove(EntityClass entity);

	void persist(EntityClass entity);

	long count();

}
