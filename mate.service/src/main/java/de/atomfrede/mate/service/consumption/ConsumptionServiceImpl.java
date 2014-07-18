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
import de.atomfrede.mate.service.email.MailService;

@Service(value = "consumptionService")
@Transactional(rollbackFor = Exception.class)
public class ConsumptionServiceImpl implements ConsumptionService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private ConsumptionDao consumptionDao;

	@Autowired
	private BottleDao bottleDao;

	@Autowired
	private MailService mailService;

	@Override
	public List<Consumption> list(long offset, long count) {
		return consumptionDao.list(offset, count);
	}

	@Override
	public List<Consumption> list(long offset, long count,
			String orderProperty, boolean desc) {
		return consumptionDao.list(offset, count, orderProperty, desc);
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
		User mUser = userDao.findById(user.getId());
		Bottle consumedBottle = bottleDao.getNextNotConsumedBottle();
		consumedBottle.consume();

		bottleDao.persist(consumedBottle);

		Consumption consumption = new Consumption();
		consumption.setBottle(consumedBottle);
		consumption.setConsumedBy(mUser);
		consumption.setConsumptionDate(new Date());

		consumptionDao.persist(consumption);

		mUser.consume(consumption);

		userDao.persist(mUser);

		maybeSendAlertMail();
	}

	@Override
	public int getConsumedBottles(User user) {
		return consumptionDao.findAllByProperty("consumedBy", user).size();
	}

	private void maybeSendAlertMail() {
		if (count() <= 4 && count() >= 1) {
			mailService.sendSupplyMail((int) count());
		} else if (count() == 0) {
			mailService.sendNoMatesMail();
		}
	}

}
