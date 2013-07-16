package de.atomfrede.mate.service.bottle;

import de.atomfrede.mate.domain.entities.bottle.Bottle;
import de.atomfrede.mate.domain.entities.user.User;
import de.atomfrede.mate.service.EntityService;

public interface BottleService extends EntityService<Bottle>{

	public void newCrate(User provider);
	
	/**
	 * Creates the given amount of bottles.
	 * 
	 * @param numberOfBottles
	 */
	public void addBottles(int numberOfBottles);
	
	public int getNumberOfNotConsumedBottles();

	
}
