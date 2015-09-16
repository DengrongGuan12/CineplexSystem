package edu.nju.cineplex.dao;

import edu.nju.cineplex.model.Account;

public interface AccountDao {
	public Account getAccountById(String id);
	public void update(Account account);

}
