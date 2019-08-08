package com.cg.service;

import java.util.Map;

import com.cg.bean.Account;

public interface AccountOperation {
	public boolean addAccount(Account ob);
	public boolean updateAccount(Account ob);
	public boolean deleteAccount(Account ob);
	public Account findAccount(Long mobile);
	public Map<Long,Account> getAllAccounts();
}
