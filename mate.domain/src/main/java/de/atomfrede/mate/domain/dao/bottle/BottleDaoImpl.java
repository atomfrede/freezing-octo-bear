package de.atomfrede.mate.domain.dao.bottle;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import de.atomfrede.mate.domain.dao.AbstractDAO;
import de.atomfrede.mate.domain.entities.bottle.Bottle;

@Repository(value = "bottleDao")
public class BottleDaoImpl extends AbstractDAO<Bottle> implements BottleDao {

	public BottleDaoImpl() {
		super(Bottle.class);
	}

	@Transactional
	public void addBottles(int numberOfBottles) {
		for (int i = 0; i < numberOfBottles; i++) {
			Bottle newBottle = new Bottle();
			getSession().saveOrUpdate(newBottle);
		}
		getSession().flush();

	}

	@Override
	@Transactional(readOnly = true)
	public Bottle getNextNotConsumedBottle() {
		Criteria crit = getSession().createCriteria(Bottle.class);
		crit.add(Restrictions.eq("consumed", false));

		return (Bottle) crit.list().get(0);
	}

	@Override
	public int getNotConsumedBottles() {
		Criteria crit = getSession().createCriteria(Bottle.class);
		crit.add(Restrictions.eq("consumed", false));
		return crit.list().size();
	}
	
	

}
