package com.objects.user;
//my user object
public class BankUser {
	private String Fname;
	private String Lname;
	private String UID;
	private String password;
	private String email; 
	private double blance;
	

	public BankUser(String fname, String lname, String uID, String password, String email, double blance) {
		super();
		this.Fname = fname;
		this.Lname = lname;
		this.UID = uID;
		this.password = password;
		this.email = email;
		this.blance = blance;
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
	public double getBlance() {
		return this.blance;
	}
	public void setBlance(double blance) {
		this.blance = blance;
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
		return this.Fname + ":" + this.Lname + ":" + this.UID + ":" + this.password + ":" + this.email + ":" + this.blance + ":"+" \r\n";
	}
	

}
