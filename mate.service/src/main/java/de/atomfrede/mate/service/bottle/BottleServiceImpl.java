package de.atomfrede.mate.service.bottle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.atomfrede.mate.domain.dao.account.AccountDao;
import de.atomfrede.mate.domain.dao.bottle.BottleDao;
import de.atomfrede.mate.domain.dao.user.UserDao;
import de.atomfrede.mate.domain.entities.bottle.Bottle;
import de.atomfrede.mate.domain.entities.user.User;

@Service(value = "bottleService")
@Transactional(rollbackFor = Exception.class)
public class BottleServiceImpl implements BottleService {

	@Autowired
	private BottleDao bottleDao;

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private AccountDao accounts;

	@Override
	public List<Bottle> list(long offset, long count) {
		return bottleDao.list(offset, count);
	}

	@Override
	public List<Bottle> list(long offset, long count, String orderProperty,
			boolean desc) {
		return bottleDao.list(offset, count, orderProperty, desc);
	}

	@Override
	public List<Bottle> findAll() {
		return bottleDao.findAll();
	}

	@Override
	public Bottle findById(Long id) {
		return bottleDao.findById(id);
	}

	@Override
	public Bottle findByProperty(String propertyName, Object propertyValue) {
		return bottleDao.findByProperty(propertyName, propertyValue);
	}

	@Override
	public void remove(Bottle entity) {
		bottleDao.remove(entity);
	}

	@Override
	public void persist(Bottle entity) {
		bottleDao.persist(entity);
	}

	@Override
	public long count() {
		return bottleDao.count();
	}

	@Transactional
	public void newCrate(User provider) {
		addBottles(20);
		provider.getAccount().increaseBy(20 * 1.00);
		userDao.persist(provider);
	}

	@Override
	public void addBottles(int numberOfBottles) {
		bottleDao.addBottles(numberOfBottles);

	}

	@Override
	public int getNumberOfNotConsumedBottles() {
		return bottleDao.getNotConsumedBottles();
	}

}
