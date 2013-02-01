package de.atomfrede.mate.service.bottle;

import de.atomfrede.mate.domain.entities.bottle.Bottle;
import de.atomfrede.mate.service.EntityService;

public interface BottleService extends EntityService<Bottle>{

	/**
	 * Creates the number of bottles that is typically inside a crate of club mate.
	 */
	public void newCrate();
	
	/**
	 * Creates the given amount of bottles.
	 * 
	 * @param numberOfBottles
	 */
	public void addBottles(int numberOfBottles);
	
	public int getNumberOfNotConsumedBottles();

	
}
