package de.atomfrede.mate.domain.entities;

public interface JsonTransferable<T extends AbstractEntity> {
	
	public T toJsonTransferable();

}
