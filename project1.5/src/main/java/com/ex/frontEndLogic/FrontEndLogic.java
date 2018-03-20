package com.ex.frontEndLogic;

import java.util.ArrayList;

import com.sql.SqlCalls;

import project1.com.objects.NA;
import project1.com.objects.PendingTransactions;
import project1.com.objects.User;

public class FrontEndLogic {
	
	SqlCalls sc = new SqlCalls();
	ArrayList<User> user = sc.getUsers();
	
	
	public FrontEndLogic() {
		for(int x = 0; x < user.size(); x++) {
			User temp = user.get(x);
			user.get(x).setPendingTransactoins(sc.getPT(temp));
			user.get(x).setNa(sc.getNA(temp));
			System.out.println(user.get(x).getPendingTransactions().toString());
		}
	}
	public double rightCost(User u, int type, double cost) {
		if(type == 1) {
			cost = cost * .8;
		}
		else if(type == 2) {
			cost = cost * .6;
		}
		else if(type == 3) {
			cost = cost * .75;
		}
		else if(type == 4) {
			cost = cost ;
		}
		else if(type == 5) {
			cost = cost * .9;
		}
		else {
			cost = cost * .3;
		}
		if(u.getReimbursementUsed() + cost <= 1000) {
			return cost;
		}
		else if(1000 - u.getReimbursementUsed() > 0) {
			return 1000 - u.getReimbursementUsed();
		}
		else {
			return 0;
		}
	}
	public void upDateBoss(User Boss, User u) {
		for(NA a : u.getNa()) {
			Boss.getNa().add(a);
			sc.addNA(user, a, Boss);
		}
	}
	public User login(String username, String pass) {
		System.out.println(user);
		for(User x : user) {
			if((x.getEmail().equals(username) || x.getUsername().equals(username)) && x.getPWD().equals(pass)) {
				return x;
			}
		}
		return null;
		
	}
	public NA addNA(User u, NA na) {
		na = sc.addNA(user, na, u);
		u.getNa().add(na);
		return na;
	}
	public PendingTransactions addPT(User u, PendingTransactions PT) {
		PT = sc.addPT(user, PT, u);
		u.getPendingTransactions().add(PT);
		return PT;
		
	}
	public User getUserID(int nu) {
		for( User x : user) {
			if (x.getEmpId() == nu) {
				return x;
			}
		}
		return null;
	}
	public User getUser(String uname) {
		for(User x : user) {
			if(x.getUsername().equals(uname)) {
				return x;
			}
		}
		return null;
	}
	public User addUser(User u) {
		for(User x : user) {
			if(x.getUsername().equals(u.getUsername())) {
				return null;
			}
		}
		user.add(u);
		sc.addUser(u);
		return u;
	}

}
