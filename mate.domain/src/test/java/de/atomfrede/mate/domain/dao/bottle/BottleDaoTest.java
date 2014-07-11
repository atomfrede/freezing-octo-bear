package de.atomfrede.mate.domain.dao.bottle;

import static org.fest.assertions.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.atomfrede.mate.domain.entities.bottle.Bottle;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:**/domain-context.xml" })
public class BottleDaoTest {

	@Autowired
	BottleDao bottleDao;
	
	@Test
	public void assertThatAddingBottlesAddsBottles() {
		bottleDao.addBottles(15);
		assertThat(bottleDao.count()).isEqualTo(15);
	}
	
	@Test
	public void assertThatConsumedBottlesReturnsCorrectBottles() {
		assertThat(bottleDao.getNotConsumedBottles()).isEqualTo(15);
	}
	
	@Test
	public void assertThatNotConsumedBottlesReturnsCorrectCount() {
		Bottle b = bottleDao.getNextNotConsumedBottle();
		b.consume();
		bottleDao.persist(b);
		
		assertThat(bottleDao.getNotConsumedBottles()).isEqualTo(14);
	}
}
