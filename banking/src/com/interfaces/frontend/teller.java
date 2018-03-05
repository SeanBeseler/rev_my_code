package com.interfaces.frontend;

import java.util.ArrayList;

import com.objects.user.BankUser;

public interface teller{
	
	
	//add balance on user account
	BankUser addBalance(BankUser user, double amount);
	
	
	//Subtract balance on user account
	BankUser subBalance (BankUser user, double amount);
	
	
	//User can view balance 
	void viewBalance (BankUser user);
	
	//User can log in
	int login (ArrayList<BankUser> acount, String username, String email, String password);
	
	//Update account info
	BankUser update (ArrayList<BankUser> acount, BankUser user);
	

}
