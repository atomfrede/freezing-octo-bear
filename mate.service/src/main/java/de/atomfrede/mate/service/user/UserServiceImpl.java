package de.atomfrede.mate.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.atomfrede.mate.domain.dao.user.UserDao;
import de.atomfrede.mate.domain.entities.user.User;

@Service(value = "userService")
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;

	@Override
	public User getByUsername(String username) {
		return userDao.getByUserName(username);
	}
	

}
