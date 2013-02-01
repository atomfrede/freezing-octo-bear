package de.atomfrede.mate.domain.dao.bottle;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import de.atomfrede.mate.domain.dao.AbstractDAO;
import de.atomfrede.mate.domain.entities.bottle.Bottle;

@Repository(value = "bottleDao")
public class BottleDaoImpl extends AbstractDAO<Bottle> implements BottleDao {

	public BottleDaoImpl() {
		super(Bottle.class);
	}

	@Override
	@Transactional(readOnly = true)
	public Bottle getNotConsumedBottle() {
		return findByProperty("consumed", false);
	}

}
