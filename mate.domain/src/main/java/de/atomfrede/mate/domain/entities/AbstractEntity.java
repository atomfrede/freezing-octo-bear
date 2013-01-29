package de.atomfrede.mate.domain.entities;

public abstract class AbstractEntity implements IEntity {

	public boolean isNew() {
        return getId() == null;
    }

    public boolean isPersisted() {
        return !isNew();
    }

}
