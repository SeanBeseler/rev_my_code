package com.objects.account;

public class Acount {
	private int ACC_ID;
	private int Balance;
	
	public Acount(int aCC_ID, int balance) {
		super();
		ACC_ID = aCC_ID;
		Balance = balance;
	}
	public int getACC_ID() {
		return ACC_ID;
	}
	public void setACC_ID(int aCC_ID) {
		ACC_ID = aCC_ID;
	}
	public int getBalance() {
		return Balance;
	}
	public void setBalance(int balance) {
		Balance = balance;
	}
	
	
}