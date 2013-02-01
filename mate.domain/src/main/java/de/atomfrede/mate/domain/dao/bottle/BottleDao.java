package de.atomfrede.mate.domain.dao.bottle;

import de.atomfrede.mate.domain.dao.DAO;
import de.atomfrede.mate.domain.entities.bottle.Bottle;

public interface BottleDao extends DAO<Bottle>{

	public Bottle getNotConsumedBottle();
	
	public void addBottles(int numberOfBottles);
}
