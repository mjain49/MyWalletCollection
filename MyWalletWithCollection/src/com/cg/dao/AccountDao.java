package com.cg.dao;

import java.util.Map;

import com.cg.bean.Account;

public interface AccountDao {
	public boolean addAccount(Account ob);
	public boolean updateAccount(Account ob);
	public boolean deleteAccount(long mobileno);
	public Account findAccount(long mobileno);
	public boolean transferMoney(Account from,Account to);

	public Map<Long, Account> getAllAccount();
	
}
