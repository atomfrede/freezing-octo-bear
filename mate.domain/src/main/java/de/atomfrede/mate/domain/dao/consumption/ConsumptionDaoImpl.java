package de.atomfrede.mate.domain.dao.consumption;

import org.springframework.stereotype.Repository;

import de.atomfrede.mate.domain.dao.AbstractDAO;
import de.atomfrede.mate.domain.entities.consumption.Consumption;

@Repository(value = "consumptionDao")
public class ConsumptionDaoImpl extends AbstractDAO<Consumption> implements ConsumptionDao {

	public ConsumptionDaoImpl() {
		super(Consumption.class);
	}

}
