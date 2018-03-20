package com.validate;

import java.util.ArrayList;

import project1.com.objects.User;

public class validate {
	public boolean isInt(String input) {
		try {
			Integer.parseInt(input);
			return true;
		}
		catch(NumberFormatException e) {
			return false;
		}
	}
	
	public int getInt(String input) {
		try {
			Integer.parseInt(input);
			return Integer.parseInt(input);
		}
		catch(NumberFormatException e) {
			return -666;
		}
	}
	public boolean isDoub(String input) {
		try {
			Double.parseDouble(input);
			return true;
		}
		catch(NumberFormatException e) {
			return false;
		}
	}
	
	public double getDoub(String input) {
		try {
			Double.parseDouble(input);
			return Double.parseDouble(input);
		}
		catch(NumberFormatException e) {
			return -666.00D;
		}
	}
	public boolean isName(String test) {
		return test.matches("[a-zA-Z]+");
	}
	
	public boolean isEmail(String test) {
		return test.matches("[a-zA-Z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,65}");
		
	}
	
	public boolean checkEmail(String test, ArrayList<User> acount) {
		for(User x : acount) {
			if(x.getEmail().equals(test)) {
				return true;
			}
		}
		return false;
	}
	public boolean checkUserName(String test, ArrayList<User> acount) {
		for(User x : acount) {
			if(x.getUsername().equals(test)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean CheckForNeg(Double num) {
		if(num < 0) {
			return true;
		}
		return false;
	}
}
