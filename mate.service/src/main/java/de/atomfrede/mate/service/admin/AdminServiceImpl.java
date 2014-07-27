package de.atomfrede.mate.service.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import de.atomfrede.mate.domain.dao.account.AccountDao;
import de.atomfrede.mate.domain.dao.bottle.BottleDao;
import de.atomfrede.mate.domain.dao.consumption.ConsumptionDao;
import de.atomfrede.mate.domain.dao.user.UserDao;
import de.atomfrede.mate.domain.entities.account.Account;
import de.atomfrede.mate.domain.entities.bottle.Bottle;
import de.atomfrede.mate.domain.entities.consumption.Consumption;
import de.atomfrede.mate.domain.entities.user.User;

@Service(value = "adminService")
@Transactional(rollbackFor = Exception.class)
public class AdminServiceImpl implements AdminService {

	@Autowired
	private UserDao userdao;

	@Autowired
	private ConsumptionDao consumptions;

	@Autowired
	private BottleDao bottles;
	
	@Autowired
	private AccountDao accounts;

	@Override
	@Transactional
	public void clearAllData() {
		for (Consumption con : consumptions.findAll()) {
			con.setBottle(null);
			con.setConsumedBy(null);
			//consumptions.remove(con);
		}
		
		for (User u : userdao.findAll()) {
			u.getAccount().reset();
			u.getConsumptions().clear();
		}

		for (Bottle b : bottles.findAll()) {
			bottles.remove(b);
		}
		
		for (Consumption con : consumptions.findAll()) {
			consumptions.remove(con);
		}
	}

	@Override
	@Transactional
	public void clearData(User user) {
		user.getAccount().reset();

		for (Consumption con : user.getConsumptions()) {
			consumptions.remove(con);
		}
	}

}
