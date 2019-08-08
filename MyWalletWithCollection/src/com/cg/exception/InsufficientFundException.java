package com.cg.exception;

public class InsufficientFundException extends Exception {
	private double balance;
	public InsufficientFundException(){
		
	}

	public InsufficientFundException(String msg,double balance) {
		super(msg);
		this.balance=balance;
	}

	@Override
	public String toString() {
		return "InsufficientFundException [balance=" + balance + "]"+super.getMessage();
	}
	
}
