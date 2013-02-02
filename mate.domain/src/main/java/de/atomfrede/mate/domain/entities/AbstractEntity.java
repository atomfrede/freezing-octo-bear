package de.atomfrede.mate.domain.entities;

@SuppressWarnings("serial")
public abstract class AbstractEntity implements IEntity{

	public boolean isNew() {
        return getId() == null;
    }

    public boolean isPersisted() {
        return !isNew();
    }
    
}
