package de.atomfrede.mate.service.user;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.atomfrede.mate.domain.dao.user.UserDao;
import de.atomfrede.mate.domain.entities.account.Account;
import de.atomfrede.mate.domain.entities.user.Role;
import de.atomfrede.mate.domain.entities.user.User;

@Service(value = "userService")
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {

	private final Log log = LogFactory.getLog(UserServiceImpl.class);

	@Autowired
	private UserDao userDao;

	@Override
	public User getByUsername(String username) {
		return userDao.getByUserName(username);
	}

	@Override
	public List<User> list(long offset, long count) {
		return userDao.list(offset, count);
	}

	@Override
	public List<User> list(long offset, long count, String orderProperty,
			boolean desc) {
		return userDao.list(offset, count, orderProperty, desc);
	}

	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}

	@Override
	public User findById(Long id) {
		return userDao.findById(id);
	}

	@Override
	public User findByProperty(String propertyName, Object propertyValue) {
		return userDao.findByProperty(propertyName, propertyValue);
	}

	@Override
	public void remove(User entity) {
		userDao.remove(entity);

	}

	@Override
	public void persist(User entity) {
		userDao.persist(entity);

	}

	@Override
	public long count() {
		return userDao.count();
	}

	@Override
	public User createUser(String username, String firstname, String lastname,
			String email, String password) throws UsernameAlreadyTakenException {
		return createUser(username, firstname, lastname, email, password, Role.User);
	}
	
	@Override
	public User createAdminUser(String username, String firstname,
			String lastname, String email, String password)
			throws UsernameAlreadyTakenException {
		return createUser(username, firstname, lastname, email, password, Role.Admin);
	}
	
	private User createUser(String username, String firstname,
			String lastname, String email, String password, Role userRole) throws UsernameAlreadyTakenException {
		User possibleUser = userDao.findByProperty("username", username);
		if (possibleUser != null) {
			log.error("Username already take. Can't create user with user name "
					+ username);
			throw new UsernameAlreadyTakenException();

		}
		User user = new User();
		user.setUsername(username);
		user.setFirstname(firstname);
		user.setLastname(lastname);
		user.setPassword(password);
		user.setEmail(email);
		user.setAccount(new Account());
		user.setRole(userRole);

		userDao.persist(user);
		return user;
	}

	@Override
	public boolean canCreateUser(String username) {
		User user = userDao.getByUserName(username);
		return user == null;
	}

	@Override
	public User deactivateUserByName(final String username) {
		User possibleUser = userDao.findByProperty("username", username);
		if(possibleUser != null) {
			possibleUser.setActive(false);
			userDao.persist(possibleUser);
			
			return possibleUser;
		} else {
			throw new IllegalArgumentException("A user with username "+username+" doesn't exist.");
		}
	}

	

}
