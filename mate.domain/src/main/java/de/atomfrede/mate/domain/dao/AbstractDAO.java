package de.atomfrede.mate.domain.dao;

import de.atomfrede.mate.domain.entities.AbstractEntity;

public abstract class AbstractDAO <EntityClass extends AbstractEntity> implements DAO<EntityClass>{

	public void persist(EntityClass entity) {
        getEntityManager().persist(entity);
    }

    public void remove(EntityClass entity) {
        getEntityManager().remove(entity);
    }


}
