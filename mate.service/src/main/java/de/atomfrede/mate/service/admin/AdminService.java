package de.atomfrede.mate.service.admin;

import de.atomfrede.mate.domain.entities.user.User;

public interface AdminService {

	/**
	 * Clears all data. After calling this method *all* accounts are reset to zero, all bottles are removed and all consumptions are removed.
	 * 
	 *  Use with caution!
	 */
	void clearAllData();
	
	/**
	 * Clears all data for a specific user. After calling this method the {@code user}s account is reset to zero and all consumptions are removed.
	 * @param user
	 */
	void clearData(User user);
}
