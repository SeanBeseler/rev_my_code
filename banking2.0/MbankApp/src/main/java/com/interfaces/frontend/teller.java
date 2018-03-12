package com.interfaces.frontend;

import java.util.ArrayList;

import com.objects.account.Acount;
import com.objects.user.BankUser;

public interface teller {
	

	//add balance on user account
	Acount addBalance(Acount acc, double amount);
	
	
	//Subtract balance on user account
	Acount subBalance (Acount acc, double amount);
	
	
	//User can view balance 
	void viewBalance (Acount acc);
	
	//User can log in
	int login (ArrayList<BankUser> acount, String username, String email, String password);
	
	//Update account info
	BankUser update (ArrayList<BankUser> acount, BankUser user);
	
	//make new account
	Acount makeNewAccount(BankUser user);

}
