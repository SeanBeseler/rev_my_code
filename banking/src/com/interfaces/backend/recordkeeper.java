package com.interfaces.backend;

import java.util.ArrayList;

import com.objects.user.BankUser;

public interface recordkeeper{
	
	//read data from file
	ArrayList<BankUser> getData ();
	
	//write data from file
	void recordData(ArrayList<BankUser> accounts);

	
}
