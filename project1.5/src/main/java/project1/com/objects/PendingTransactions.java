package project1.com.objects;

import java.util.ArrayList;

public class PendingTransactions {
	private int ID = 0;
	private double amount;
	private String FileID;
	private int type;
	private boolean aproved = false;
	private String notes;
	private int urgent = 0;
	private String StartTime;
	private String EndTime;
	private String minGrad;
	private boolean DSA = false;
	private boolean DHA = false;
	private boolean BenCoA = false;
	
	
	public PendingTransactions( double amount, String fileID, int type, String notes,
			String startTime, String endTime, String minGrad) {
		super();
		this.amount = amount;
		FileID = fileID;
		this.type = type;
		this.notes = notes;
		StartTime = startTime;
		EndTime = endTime;
		this.minGrad = minGrad;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getFileID() {
		return FileID;
	}
	public void setFileID(String fileID) {
		FileID = fileID;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public boolean isAproved() {
		return aproved;
	}
	public void setAproved(boolean aproved) {
		this.aproved = aproved;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public int getUrgent() {
		return urgent;
	}
	public void setUrgent(int urgent) {
		this.urgent = urgent;
	}
	public String getStartTime() {
		return StartTime;
	}
	public void setStartTime(String startTime) {
		StartTime = startTime;
	}
	public String getEndTime() {
		return EndTime;
	}
	public void setEndTime(String endTime) {
		EndTime = endTime;
	}
	public String getMinGrad() {
		return minGrad;
	}
	public void setMinGrad(String minGrad) {
		this.minGrad = minGrad;
	}
	public boolean isDSA() {
		return DSA;
	}
	public void setDSA(boolean dSA) {
		DSA = dSA;
	}
	public boolean isDHA() {
		return DHA;
	}
	public void setDHA(boolean dHA) {
		DHA = dHA;
	}
	public boolean isBenCoA() {
		return BenCoA;
	}
	public void setBenCoA(boolean benCoA) {
		BenCoA = benCoA;
	}
	
	

}
