package de.atomfrede.mate.domain.entities.user;

import java.util.List;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import de.atomfrede.mate.domain.dao.user.UserDao;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( { "../../../../../../domain-context.xml" })
public class UserDaoTest {
	
	@Autowired
	ApplicationContext context;
	
	@Autowired
	UserDao userDao;
	
	@Before
	public void addUser(){
		User user = new User();
		user.setEmail("max@muster.org");
		user.setFirstname("Max");
		user.setLastname("Muster");
		user.setUsername("mmuster");
		userDao.persist(user);	
	}
	
	@Test
	public void findAllTest(){
		List<User> allUsers = userDao.findAll();
		assertNotNull(allUsers);
		assertEquals(1, allUsers.size());
	}
	
	@Test
    public void getUserById() {
		
	}

}
