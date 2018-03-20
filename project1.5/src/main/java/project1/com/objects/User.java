package project1.com.objects;

import java.util.ArrayList;

public class User {
	private String username;
	private String Fname;
	private String Lname;
	private String email;
	private int reportsto;
	private int jobId;
	private int empId;
	private String PWD;
	private ArrayList<messages> mes;
	private double ReimbursementUsed;
	private ArrayList<PendingTransactions> PT;
	private ArrayList<NA> na;
	
	public User() {
		super();
		this.username = null;
		this.Fname = null;
		this.Lname = null;
		this.email = null;
		this.reportsto = 0;
		this.jobId = 0;
		this.empId = 0;
		this. PWD = null;
		this.mes = new ArrayList<messages>();
		this.ReimbursementUsed = 0.00D;
		this.PT = new ArrayList<PendingTransactions>();
		this.na = new ArrayList<NA>();
		
	}
	public User(String username, String fname, String lname, String email, int reportsto, int jobId, int empId,
			String pWD) {
		super();
		this.username = username;
		this.Fname = fname;
		this.Lname = lname;
		this.email = email;
		this.reportsto = reportsto;
		this.jobId = jobId;
		this.empId = empId;
		this. PWD = pWD;
		this.mes = new ArrayList<messages>();
		this.ReimbursementUsed = 0.00D;
		this.PT = new ArrayList<PendingTransactions>();
		this.na = new ArrayList<NA>();
	}
	
	
	@Override
	public String toString() {
		return "User [username=" + username + ", Fname=" + Fname + ", Lname=" + Lname + ", email=" + email
				+ ", reportsto=" + reportsto + ", jobId=" + jobId + ", empId=" + empId + ", PWD=" + PWD + ", mes=" + mes
				+ ", ReimbursementUsed=" + ReimbursementUsed + ", PT=" + PT + "]";
	}
	
	
	public ArrayList<NA> getNa() {
		return na;
	}
	public void setNa(ArrayList<NA> na) {
		this.na = na;
	}
	public ArrayList<PendingTransactions> getPendingTransactions() {
		return this.PT;
		
	}
	
	public void setPendingTransactoins( ArrayList<PendingTransactions> pt) {
		this.PT = pt;
	}
	public  double getReimbursementUsed() {
		return this.ReimbursementUsed;
	}
	public  void setReimbursementUsed(double ReimbursementUsed) {
		this.ReimbursementUsed = ReimbursementUsed;
	}
	public  String getUsername() {
		return username;
	}
	public  void setUsername(String username) {
		this.username = username;
	}
	public  String getFname() {
		return Fname;
	}
	public  void setFname(String fname) {
		Fname = fname;
	}
	public  String getLname() {
		return Lname;
	}
	public  void setLname(String lname) {
		Lname = lname;
	}
	public  String getEmail() {
		return email;
	}
	public  void setEmail(String email) {
		this.email = email;
	}
	public  int getReportsto() {
		return reportsto;
	}
	public  void setReportsto(int reportsto) {
		this.reportsto = reportsto;
	}
	public  int getJobId() {
		return jobId;
	}
	public  void setJobId(int jobId) {
		this.jobId = jobId;
	}
	public  int getEmpId() {
		return empId;
	}
	public  void setEmpId(int empId) {
		this.empId = empId;
	}
	public  String getPWD() {
		return PWD;
	}
	public  void setPWD(String pWD) {
		PWD = pWD;
	}
	public  ArrayList<messages> getMes() {
		return mes;
	}
	public  void setMes(ArrayList<messages> mes) {
		this.mes = mes;
	}
	
	

}
