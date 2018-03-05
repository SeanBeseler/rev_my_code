package com.startApp;

import java.util.ArrayList;
import java.util.Scanner;

import com.logic.backend.BackEndLogic;
import com.logic.frontend.FrontEndLogic;
import com.logic.frontend.validate.validate;
import com.objects.user.BankUser;

public class StartApp {
	public void menu() {
		BackEndLogic BEL = new BackEndLogic();
		FrontEndLogic FEL = new FrontEndLogic();
		final ArrayList<BankUser> acount = BEL.getData();
		final validate checkinfo = new validate();
		int user = 0;
		int state = 0;
		boolean running = true;
		Scanner re = new Scanner(System.in);
		while(running) {
			switch(state) {
			case 0:
				System.out.println("Hello welcome to the I-Scama Bank! If you would like to log in press 1. If you would like to make a free account press 2. If you would like to exit press 3.");
				String input = re.next();
				switch(input) {
				case "1":
					boolean needinfo = true;
					String uname = "";
					String email = "";
					String password = "";
					String input2 = null;
					while(needinfo) {
						System.out.println("Press 1 to enter your username or press 2 to enter your email.");
						input2 = re.next();
						if(checkinfo.CheckFor1Or2(input2)) {
							needinfo = false;
						}
					}
					switch(input2) {
					case "1" :
						System.out.println("Enter username: ");
						uname = re.next();
						break;
					case "2" :
						System.out.println("Enter email: ");
						email = re.next();
					}
					System.out.println("Enter password");
					password = re.next();
					int real = FEL.login(acount, uname, email, password);
					if(real == 0) {
						System.out.println("Sorry but that user name or email  and/or password does exits.");
					}
					else {
						real--;
						user = real;
						state = 1;
					}
					break;
				case "3":
					running = false;
					break;
				case "2":
					int point = 0;
					String name1 = "";
					String name2 = "";
					String emale = "";
					String userid = "";
					String passwo = "";
					while(point < 6) {
						if(point == 0) {
							System.out.println("Please enter your first name. ");
							name1 = re.next();
							if(checkinfo.isName(name1)) {
								point++;
							}
							else {
								System.out.println("Pleae input a US legal name");
							}
						}
						else if(point == 1) {
							System.out.println("Please enter your last name. ");
							name2 = re.next();
							if(checkinfo.isName(name2)) {
								point++;
							}
							else {
								System.out.println("Please input a US legal name");
							}
						}
						else if(point == 2) {
							System.out.println("Please enter your email name. ");
							emale = re.next();
							if(checkinfo.isEmail(emale)) {
								if(checkinfo.checkEmail(emale, acount)) {
									point++;
								}
								else {
									System.out.println("The email is in our system please input another.");
								}
							}
							else {
								System.out.println("Please input a valid email.");
							}
						}
						else if(point == 3) {
							System.out.println("Please enter your username. ");
							userid = re.next();
							if(checkinfo.checkUserName(userid, acount)) {
								point++;
							}
							else {
								System.out.println("Username is in our system please input another.");
							}
						}
						else if(point == 4) {
							System.out.println("Please enter your password. ");
							passwo = re.next();
							point++;
						}
						else {
							BankUser us = new BankUser(name1, name2, userid, passwo, emale, 0.00);
							acount.add(us);
							BEL.recordData(acount);
							System.out.println("Your account has been added please log in.");
							point++;
						}
					}
					break;
				
					default :
						System.out.println("Please input a valid choice");
				}
            break;
			case 1:
				while(true) {
					System.out.println("Welcome " + acount.get(user).getFname() + " " + acount.get(user).getLname());
					System.out.println("If you would like to view you balance press 1, to make a deposit press 2, to make withdraw press 3, to change account info press 4, to log out press 5.");
					String input2 = re.next();
					double amount;
					BankUser temp;
					switch(input2) {
					case "4":
						BankUser tempup = FEL.update(acount, acount.get(user));
						acount.set(user, tempup);
						BEL.recordData(acount);
						break;
					case "1":
						FEL.viewBalance(acount.get(user));
						break;
						
					case "2":
						
						System.out.println("Please input the amount you want to deposit: ");
						String userinput = re.next();
						if(checkinfo.isDoub(userinput)) {
							amount = Double.parseDouble(userinput);//checkinfo.getDoub(userinput);
							if(!checkinfo.CheckForNeg(amount)) {
								temp = FEL.addBalance(acount.get(user), amount);
								acount.set( user, temp);
								System.out.println("Your balance has been updated!");
								BEL.recordData(acount);
							}
							else {
								System.out.println("Please input a postive number.");
							}
						}
						break;
					
					case "3":
						System.out.println("Pleae input the amount you want to withdarw: ");
						String userinput2 = re.next();
						double currbal = acount.get(user).getBlance();
						temp = null;
						if(checkinfo.isDoub(userinput2)) {
							amount = Double.parseDouble(userinput2);//checkinfo.getDoub(userinput2);
							if(!checkinfo.CheckForNeg(amount)) {
								temp = FEL.subBalance(acount.get(user), amount);
								if(temp.getBlance() == -1) {
									acount.get(user).setBlance(currbal);
									System.out.println("There is not enough money the account.");
								}
								else {
									acount.set(user, temp);
									BEL.recordData(acount);
									System.out.println("Your balance has been updated!");
							
								}
							}
							else {
								System.out.println("Please input a postive number.");
							}
						}
							
						break;
					
					case "5":
						state = 0;
						System.out.println("You are logged out.");
						break;
						
						
					}
					break;
				}
				
			}
		}
      re.close();
		
	}
	public static void main(String[] args) {
		
		StartApp sapp = new StartApp();
		sapp.menu();


	}

}
