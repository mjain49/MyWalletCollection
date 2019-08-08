package com.cg.dao;

import java.util.HashMap;
import java.util.Map;

import com.cg.bean.Account;

public  class AccountDaoImpl implements AccountDao {
	
	public static Map<Long,Account>accmap=new HashMap<Long,Account>();
	
	@Override
	public boolean addAccount(Account ob) {
		accmap.put(ob.getMobile(),ob);
		return true;
	}

	@Override
	public boolean updateAccount(Account ob) {
		accmap.put(ob.getMobile(),ob);
		return true;
	}
	@Override
	public Account findAccount(long mobileno) {
		// TODO Auto-generated method stub
		return accmap.get(mobileno);
	}

	@Override
	public boolean transferMoney(Account from, Account to) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteAccount(long mobileno) {
		accmap.remove(mobileno);
		return true;
	}

	@Override
	public Map<Long, Account> getAllAccount() {
		// TODO Auto-generated method stub
		return accmap;
	}
/*
	@Override
	public boolean deleteAccount(Account ob) {
		// TODO Auto-generated method stub
		return false;
	}
*/
	



}
