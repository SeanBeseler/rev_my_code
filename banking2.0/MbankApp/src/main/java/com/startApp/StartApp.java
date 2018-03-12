package com.startApp;

import java.util.ArrayList;
import java.util.Scanner;

import com.logic.backend.BackEndLogic;
import com.logic.frontend.FrontEndLogic;
import com.logic.frontend.validate.validate;
import com.objects.account.Acount;
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
							BankUser us = new BankUser(name1, name2, userid, passwo, emale);
							acount.add(us);
							String[] info = {name1, name2, userid, passwo, emale};
							BEL.recordData(1, info);
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
				boolean runningg = true;
				while(runningg) {
					System.out.println("Welcome " + acount.get(user).getFname() + " " + acount.get(user).getLname());
					System.out.println("If you would like to view an account balance press 1, to make a deposit press 2, to make withdraw press 3, to transfer money between accounts press 4, to view your accounts press 5, to add an account press 6, to log out press 7.");
					String input2 = re.next();
					double amount;
					BankUser temp;
					switch(input2) {
					case "4":
						Acount one = null;
						Acount two = null; 
						System.out.println("Please input your first account: ");
						String input5 = re.next();
						if(checkinfo.isDoub(input5)) {
							if(checkinfo.CheckForAccount(Integer.parseInt(input5), acount.get(user).getAcounts())){
								int xxx = 0 ;
								for (int xx = 0; xx < acount.get(user).getAcounts().size(); xx++) {
									if(Integer.parseInt(input5) == acount.get(user).getAcounts().get(xx).getACC_ID()) {
										one = acount.get(user).getAcounts().get(xx);
										xxx = xx;
									}
								}
								System.out.println("Pleae input your second account: ");
								input5 = re.next();
								if(checkinfo.isDoub(input5)) {
									if(checkinfo.CheckForAccount(Integer.parseInt(input5), acount.get(user).getAcounts())){
										int xxxx = 0 ;
										for (int xx = 0; xx < acount.get(user).getAcounts().size(); xx++) {
											if(Integer.parseInt(input5) == acount.get(user).getAcounts().get(xx).getACC_ID()) {
												two = acount.get(user).getAcounts().get(xx);
												xxxx = xx;
											}
										}
										System.out.println("Enter the amount you want to trasfer: ");
										String am = re.next();
										if(checkinfo.isDoub(am)) {
											if(acount.get(user).getAcounts().get(xxx).getBalance() >=  0) {
												two = FEL.addBalance(two, Double.parseDouble(am));
												one = FEL.subBalance(one, Double.parseDouble(am));
												acount.get(user).getAcounts().set(xxxx, two);
												acount.get(user).getAcounts().set(xxx, one);
											}
											else {
												System.out.println("Not enought money in the account.");
											}
										}
									}
								}
							}
						}
						break;
					case "6":
						acount.get(user).getAcounts().add(FEL.makeNewAccount(acount.get(user)));
						
					case "5":
						System.out.println("Your accounts:");
						for(Acount x : acount.get(user).getAcounts()) {
							System.out.println(x.getACC_ID());
						}
						break;
					case "1":
						System.out.println("Please enter the account number you wish to view your balance.");
						input2 = re.next();
						if(checkinfo.isDoub(input2)) {
							if(checkinfo.CheckForAccount(Integer.parseInt(input2),acount.get(user).getAcounts())) {
								Acount ac = null ; 
								for (Acount x : acount.get(user).getAcounts()) {
									if(Integer.parseInt(input2) == x.getACC_ID()) {
										ac = x;
									}
								}
								FEL.viewBalance(ac);
							}
						}
						break;
						
					case "2":
						System.out.println("Pleae input the account you want to make a deposit to: ");
						String userinput = re.next();
						if(checkinfo.isDoub(userinput)) {
							if(checkinfo.CheckForAccount(Integer.parseInt(userinput), acount.get(user).getAcounts())) {
								System.out.println("Please input the amount you want to deposit: ");
								userinput = re.next();
								if(checkinfo.isDoub(userinput)) {
									amount = Double.parseDouble(userinput);//checkinfo.getDoub(userinput);
									if(!checkinfo.CheckForNeg(amount)) {
										Acount ac = null ;
										int xxx = 0 ;
										for (int xx = 0; xx < acount.get(user).getAcounts().size(); xx++) {
											if(Integer.parseInt(input2) -1 == acount.get(user).getAcounts().get(xx).getACC_ID()) {
												ac = acount.get(user).getAcounts().get(xx);
												xxx = xx;
											}
										}
										ac = FEL.addBalance(ac, amount);
										acount.get(user).getAcounts().set(xxx, ac);
										System.out.println("Your balance has been updated!");
										/// NEEDS WORK BEL.recordData(acount);
									}
									else {
										System.out.println("Please input a postive number.");
									}
								}
							}
						}
						break;
					
					case "3":
						System.out.println("Pleae input the account you want to withdraw from : ");
						String userinput22 = re.next();
						if(checkinfo.isDoub(userinput22)) {
							if(checkinfo.CheckForAccount(Integer.parseInt(userinput22), acount.get(user).getAcounts())) {
								System.out.println("Pleae input the amount you want to withdarw: ");
								String userinput2 = re.next();
								Acount ac = null ;
								int xxx = 0 ;
								for (int xx = 0; xx < acount.get(user).getAcounts().size(); xx++) {
									if(Integer.parseInt(userinput22) == acount.get(user).getAcounts().get(xx).getACC_ID()) {
										ac = acount.get(user).getAcounts().get(xx);
										xxx = xx;
									}
								}
								double currbal = ac.getBalance();
								temp = null;
								if(checkinfo.isDoub(userinput2)) {
									amount = Double.parseDouble(userinput2);//checkinfo.getDoub(userinput2);
									if(!checkinfo.CheckForNeg(amount)) {
										ac = FEL.subBalance(acount.get(user).getAcounts().get(xxx), amount);
										if(ac.getBalance() == -1) {
											acount.get(user).getAcounts().get(xxx).setBalance(currbal);
											System.out.println("There is not enough money the account.");
										}
										else {
											acount.get(user).getAcounts().get(xxx).setBalance(ac.getBalance());
											System.out.println("Your balance has been updated!");
							
										}
									}
									else {
										System.out.println("Please input a postive number.");
									}
								}
							}
						}
						break;
							
					
					case "7":
						state = 0;
						runningg = false;
						System.out.println("You are logged out.");
						break;
						
						
					}
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
