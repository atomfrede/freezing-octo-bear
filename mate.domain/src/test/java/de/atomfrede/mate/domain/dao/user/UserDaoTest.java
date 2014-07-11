package de.atomfrede.mate.domain.dao.user;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.atomfrede.mate.domain.entities.account.Account;
import de.atomfrede.mate.domain.entities.user.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:**/domain-context.xml" })
public class UserDaoTest {

	@Autowired
	UserDao userDao;

	User testUser;

	@Before
	public void addUser() {
		testUser = new User();
		testUser.setEmail("max@muster.org");
		testUser.setFirstname("Max");
		testUser.setLastname("Muster");
		testUser.setUsername("mmuster");
		testUser.setAccount(new Account());
		userDao.persist(testUser);
	}

	@After
	public void clear() {
		if (testUser != null) {
			userDao.remove(testUser);
		}
	}

	@Test
	public void assertThatNotPersitedUserIsNew() {
		User testUser2 = new User();
		assertThat(testUser2.isNew()).isTrue();
	}
	
	@Test
	public void assertThatPersistedUserIsNotNew() {
		assertThat(testUser.isNew()).isFalse();
	}
	
	@Test
	public void findAllTest() {
		List<User> allUsers = userDao.findAll();
		assertNotNull(allUsers);
		assertEquals(1, allUsers.size());
	}

	@Test
	public void deleteTest() {
		long id = testUser.getId();
		userDao.remove(testUser);
		testUser = null;
		User found = userDao.findById(id);
		assertThat(found).isNull();
	}

	@Test
	public void assertThatCanBeFoundByUsername() {
		User found = userDao.getByUserName("mmuster");
		assertThat(found).isNotNull();
		assertThat(found.getUsername()).isEqualTo("mmuster");
	}

	@Test
	public void assertThatListReturnsCorrectData() {
		// Second TestUser
		User testUser2 = new User();
		testUser2.setEmail("max@muster.org");
		testUser2.setFirstname("Max");
		testUser2.setLastname("Muster2");
		testUser2.setUsername("mmuster2");
		testUser2.setAccount(new Account());
		userDao.persist(testUser2);

		List<User> retrieved = userDao.list(0, 1);

		assertThat(retrieved).isNotEmpty();
		assertThat(retrieved.size()).isEqualTo(1);
		assertThat(retrieved.get(0).getUsername()).isEqualTo("mmuster");
		
		userDao.remove(testUser2);
	}

	@Test
	public void assertThatListCanBeOrderedAsc() {
		// Second TestUser
		User testUser2 = new User();
		testUser2.setEmail("max@muster.org");
		testUser2.setFirstname("Max");
		testUser2.setLastname("Muster2");
		testUser2.setUsername("amuster");
		testUser2.setAccount(new Account());
		userDao.persist(testUser2);

		List<User> retrieved = userDao.list(0, 15, "username", false);

		assertThat(retrieved).isNotEmpty();
		assertThat(retrieved.get(0).getUsername()).isEqualTo("amuster");
		
		userDao.remove(testUser2);
	}

	@Test
	public void assertThatListCanBeOrderedDesc() {
		// Second TestUser
		User testUser2 = new User();
		testUser2.setEmail("max@muster.org");
		testUser2.setFirstname("Max");
		testUser2.setLastname("Muster2");
		testUser2.setUsername("amuster");
		testUser2.setAccount(new Account());
		userDao.persist(testUser2);

		List<User> retrieved = userDao.list(0, 15, "username", true);

		assertThat(retrieved).isNotEmpty();
		assertThat(retrieved.get(0).getUsername()).isEqualTo("mmuster");
		
		userDao.remove(testUser2);
	}
	
	@Test
	public void assertThatCountReturnsCorrectCount(){
		assertThat(userDao.count()).isEqualTo(1L);
	}
	

}
