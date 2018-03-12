package com.logic.frontend;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

import com.interfaces.frontend.teller;
import com.logic.frontend.validate.validate;
import com.objects.account.Acount;
import com.objects.user.BankUser;
import com.sql.SqlCalls;


public class FrontEndLogic implements teller {
	SqlCalls cs = new SqlCalls();
	
	@Override
	public Acount makeNewAccount(BankUser user) {
	Acount temp = new Acount (cs.makeAcount(user.getUID()), 0.00 );
	return temp;
	}

	@Override
	public Acount addBalance(Acount acc, double amount) {
		acc.setBalance(acc.getBalance() + amount);
		cs.updateAcountBalance(acc.getBalance(),acc.getACC_ID());
		return acc;
	}

	@Override
	public Acount subBalance(Acount acc, double amount) {
		if((acc.getBalance() - amount) >= 0) {
			acc.setBalance(acc.getBalance() - amount);
			cs.updateAcountBalance(acc.getBalance(),acc.getACC_ID());
		}
		else {
			acc.setBalance(-1);
		}
		return acc;
	}

	@Override
	public void viewBalance(Acount acc) {
		DecimalFormat df = new DecimalFormat("####0.00");
		System.out.println("Your balance is $" + df.format(acc.getBalance()));
		
	}

	@Override
	public int login(ArrayList<BankUser> acount, String username, String email, String password) {
		int counter = 0;
		for(BankUser x : acount) {
			if((x.getUID().equals(username) || x.getEmail().equals(email)) && x.getPassword().equals(password)) {
				counter++;
				return counter;
			}
         counter++;
		}
		
		return 0 ;
	}

	@Override
	public BankUser update(ArrayList<BankUser> acount, BankUser user) {
		Scanner re = new Scanner(System.in);
		validate val = new validate();
		System.out.println("Press 1 to change First Name. Press 2 to change Last Name. Press 3 to change UserName. Press 4 to change email. Press 5 to change password.");
		String input = re.next();
		switch(input) {
		case "4":
			System.out.println("Enter your new email.");
			String email = re.next();
			int notin1 = 0;
			for (BankUser x : acount) {
				if(x.getUID().equals(email)) {
					notin1 = 1;
				}
			}
			if(notin1 == 0) {
				user.setUID(email);
			}
			else {
				System.out.println("That email is in our system. Please enter a new one.");
			}
			break;
		case "3":
			System.out.println("Enter your new Username.");
			String username = re.next();
			int notin = 0;
			for (BankUser x : acount) {
				if(x.getUID().equals(username)) {
					notin = 1;
				}
			}
			if(notin == 0) {
				user.setUID(username);
			}
			else {
				System.out.println("That name is in our system. Please enter a new one.");
			}
			break;
		case "5":
			System.out.println("Enter your new password.");
			String password = re.next();
			user.setPassword(password);
			break;
		case "2":
			System.out.println("Enter your new last name.");
			String name2 = re.next();
			if(val.isName(name2)) {
				user.setLname(name2);
			}
			else {
				System.out.println("Please enter a vaild Name.");
			}
			break;
		case "1":
			System.out.println("Enter your new frist name.");
			String name1 = re.next();
			if(val.isName(name1)) {
				user.setFname(name1);
			}
			else {
				System.out.println("Please enter a vaild Name.");
			}
			break;
			
			
		default :
			System.out.println("Please input a vaild option.");
			break;
		}
		re.close();
		return user;
		
	}
	
	

}
