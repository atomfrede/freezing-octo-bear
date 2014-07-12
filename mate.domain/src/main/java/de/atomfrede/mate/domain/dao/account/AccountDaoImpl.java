package de.atomfrede.mate.domain.dao.account;

import org.springframework.stereotype.Repository;

import de.atomfrede.mate.domain.dao.AbstractDAO;
import de.atomfrede.mate.domain.entities.account.Account;

@Repository(value = "accountDao")
public class AccountDaoImpl extends AbstractDAO<Account> implements AccountDao {

	public AccountDaoImpl() {
		super(Account.class);
	}
}
