package de.atomfrede.mate.service.consumption;

import de.atomfrede.mate.domain.entities.consumption.Consumption;
import de.atomfrede.mate.domain.entities.user.User;
import de.atomfrede.mate.service.EntityService;

public interface ConsumptionService extends EntityService<Consumption> {
	
	/**
	 * Consumes a not consumed bottle of club mate.
	 * 
	 * After executing this method there is a new consumption dataset, one bottle less not consumed yet
	 * and the account value has decreased by the corresponding value.
	 * @param user
	 */
	public void consumeBottle(User user);
	
	public int getConsumedBottles(User user);

}
