package edu.nju.cineplex.dao.impl;

import edu.nju.cineplex.dao.AccountDao;
import edu.nju.cineplex.dao.BaseDao;
import edu.nju.cineplex.model.Account;

public class AccountDaoImpl implements AccountDao{

	private static AccountDaoImpl accountDaoImpl=new AccountDaoImpl();
	private AccountDaoImpl(){
		
	}
	public static AccountDaoImpl getInstance(){
		return accountDaoImpl;
	}
	private BaseDao baseDao=BaseDaoImpl.getInstance();
	@Override
	public Account getAccountById(String id) {
		// TODO Auto-generated method stub
		
		return (Account)baseDao.load(Account.class, id);
	}
	@Override
	public void update(Account account) {
		// TODO Auto-generated method stub
		baseDao.update(account);
		
	}
	

}
