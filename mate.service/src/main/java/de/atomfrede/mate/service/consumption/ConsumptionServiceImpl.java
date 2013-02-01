package de.atomfrede.mate.service.consumption;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.atomfrede.mate.domain.dao.bottle.BottleDao;
import de.atomfrede.mate.domain.dao.consumption.ConsumptionDao;
import de.atomfrede.mate.domain.dao.user.UserDao;
import de.atomfrede.mate.domain.entities.bottle.Bottle;
import de.atomfrede.mate.domain.entities.consumption.Consumption;
import de.atomfrede.mate.domain.entities.user.User;

@Service(value = "consumptionService")
@Transactional(rollbackFor = Exception.class)
public class ConsumptionServiceImpl implements ConsumptionService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private ConsumptionDao consumptionDao;

	@Autowired
	private BottleDao bottleDao;

	@Override
	public List<Consumption> list(long offset, long count) {
		return consumptionDao.list(offset, count);
	}

	@Override
	public List<Consumption> findAll() {
		return consumptionDao.findAll();
	}

	@Override
	public Consumption findById(Long id) {
		return consumptionDao.findById(id);
	}

	@Override
	public Consumption findByProperty(String propertyName, Object propertyValue) {
		return consumptionDao.findByProperty(propertyName, propertyValue);
	}

	@Override
	public void remove(Consumption entity) {
		consumptionDao.remove(entity);
	}

	@Override
	public void persist(Consumption entity) {
		consumptionDao.persist(entity);

	}

	@Override
	public long count() {
		return consumptionDao.count();
	}

	@Override
	@Transactional
	public void consumeBottle(User user) {
		Bottle consumedBottle = bottleDao.getNotConsumedBottle();
		consumedBottle.consume();
		
		bottleDao.persist(consumedBottle);
		
		Consumption consumption = new Consumption();
		consumption.setBottle(consumedBottle);
		consumption.setConsumedBy(user);
		consumption.setConsumptionDate(new Date());
		
		consumptionDao.persist(consumption);
		
		user.consume(consumption);
		
		userDao.persist(user);

	}
}
