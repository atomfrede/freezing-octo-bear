package de.atomfrede.mate.domain.entities.user;

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

import de.atomfrede.mate.domain.dao.user.UserDao;
import de.atomfrede.mate.domain.entities.account.Account;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:**/domain-context.xml"})
public class UserDaoTest {
	
	@Autowired
	UserDao userDao;
	
	User testUser;
	
	@Before
	public void addUser(){
		testUser = new User();
		testUser.setEmail("max@muster.org");
		testUser.setFirstname("Max");
		testUser.setLastname("Muster");
		testUser.setUsername("mmuster");
		testUser.setAccount(new Account());
		userDao.persist(testUser);	
	}
	
	@After
	public void clear(){
		userDao.remove(testUser);
	}
	
	@Test
	public void findAllTest(){
		List<User> allUsers = userDao.findAll();
		assertNotNull(allUsers);
		assertEquals(1, allUsers.size());
	}
	
	@Test
	public void deleteTest(){
		
	}

}
