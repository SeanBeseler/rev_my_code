package com.logic.frontend.validate;


import java.util.ArrayList;

import com.objects.user.BankUser;

public class validate {
	
	public boolean isDoub(String test) {
		try {
			double TNT = Double.parseDouble(test);
			
		}
		catch(NumberFormatException e) {
			System.out.println("Please input a number.");
			return false;
		}
		return true;
		
	}
	public double getDoub(String test) {
		double TNT;
		try {
			TNT = Double.parseDouble(test);
			
		}
		catch(NumberFormatException e) {
			System.out.println("Please input a number.");
			return 0;
		}
		return TNT;
		
	}
	
	public boolean isName(String test) {
		return test.matches("[a-zA-Z]+");
	}
	
	public boolean isEmail(String test) {
		return test.matches("[a-zA-Z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,65}");
		
	}
	public boolean checkEmail(String test, ArrayList<BankUser> acount) {
		for(BankUser x : acount) {
			if(x.getEmail().equals(test)) {
				return false;
			}
		}
		return true;
	}
	public boolean checkUserName(String test, ArrayList<BankUser> acount) {
		for(BankUser x : acount) {
			if(x.getUID().equals(test)) {
				return false;
			}
		}
		return true;
	}
	
	public boolean CheckFor1Or2(String test) {
		return(test.equals("1") || test.equals("2"));
		
	}
	public boolean CheckForNeg(Double num) {
		if(num < 0) {
			return true;
		}
		return false;
	}

}
