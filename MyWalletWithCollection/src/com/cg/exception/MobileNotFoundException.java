package com.cg.exception;

public class MobileNotFoundException extends Exception {
	private String Meassage;

	public MobileNotFoundException(String meassage) {
		super();
		this.Meassage = meassage;
	}

	public MobileNotFoundException() {
		
	}

	@Override
	public String toString() {
		return "MobileNotFoundException [Meassage=" + Meassage + "]"+super.getMessage();
	}

	
}
