package de.atomfrede.mate.domain.dao.bottle;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.FlushMode;
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

	@Transactional
	public void addBottles(int numberOfBottles) {
		for (int i = 0; i < numberOfBottles; i++) {
			Bottle newBottle = new Bottle();
			getSession().saveOrUpdate(newBottle);
		}
		getSession().flush();

	}

}
