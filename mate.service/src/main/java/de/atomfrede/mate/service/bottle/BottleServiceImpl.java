package de.atomfrede.mate.service.bottle;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.atomfrede.mate.domain.dao.bottle.BottleDao;
import de.atomfrede.mate.domain.entities.bottle.Bottle;

@Service(value = "bottleService")
@Transactional(rollbackFor = Exception.class)
public class BottleServiceImpl implements BottleService {

	@Autowired
	private BottleDao bottleDao;

	@Override
	public List<Bottle> list(long offset, long count) {
		return bottleDao.list(offset, count);
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

	@Override
	public void newCrate() {
		addBottles(20);
	}

	@Override
	public void addBottles(int numberOfBottles) {
		bottleDao.addBottles(numberOfBottles);

	}

}
