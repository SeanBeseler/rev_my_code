package com.objects.user;

import java.util.ArrayList;

import com.objects.account.Acount;

public class BankUser {
	private String Fname;
	private String Lname;
	private String UID;
	private String password;
	private String email; 
	private ArrayList<Acount> accounts;
	
	
	public BankUser(String fname, String lname, String uID, String password, String email, ArrayList<Acount> accounts) {
		super();
		this.Fname = fname;
		this.Lname = lname;
		this.UID = uID;
		this.password = password;
		this.email = email;
		this.accounts = accounts;
	}

	public BankUser(String fname, String lname, String uID, String password, String email) {
		super();
		this.Fname = fname;
		this.Lname = lname;
		this.UID = uID;
		this.password = password;
		this.email = email;
		this.accounts = new ArrayList<Acount>();
	}
	public String getFname() {
		return this.Fname;
	}
	public void setFname(String fname) {
		this.Fname = fname;
	}
	public String getLname() {
		return this.Lname;
	}
	public void setLname(String lname) {
		this.Lname = lname;
	}
	public String getUID() {
		return this.UID;
	}
	public void setUID(String uID) {
		UID = uID;
	}
	public ArrayList<Acount> getAcounts() {
		return this.accounts;
	}
	public void setBlance(ArrayList<Acount> account) {
		this.accounts = account;
	}
	public String getPassword() {
		return this.password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return this.email;
	}
	public void setEmail(String email) {
		this.email = email;
	} 
	
	@Override
	public String toString() {
		return this.Fname + ":" + this.Lname + ":" + this.UID + ":" + this.password + ":" + this.email + ":" + this.accounts.toString() + ":"+" \r\n";
	}
	

}
