package de.atomfrede.mate.domain.entities;

public abstract class AbstractEntity implements IEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8708443933550448049L;

	public boolean isNew() {
        return getId() == null;
    }

    public boolean isPersisted() {
        return !isNew();
    }

}
