package com.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.sql.util.ConnectionFactory;

import project1.com.objects.NA;
import project1.com.objects.PendingTransactions;
import project1.com.objects.User;

public class SqlCalls {
	public NA addNA(ArrayList<User> u, NA PT, User us) {
		int newID = 0;
		int flag = 0;
		for(User x : u) {
			for(NA y : x.getNa()) {
				if(y.getID() >= newID) {
					newID = y.getID();
					flag = 1;
				}
			}
			
		}
		if(flag == 1) {
			newID++;
		}
		PT.setID(newID);
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection();){
			String sql = "INSERT INTO NA VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps =  conn.prepareCall(sql);
			ps.setInt(1, PT.getID());
			ps.setDouble(2, PT.getAmount());
			ps.setString(3, PT.getFileID());
			ps.setInt(4, PT.getType());
			if(PT.isAproved()) {
				ps.setInt(5, 1);
			}
			else {
				ps.setInt(5, 0);
			}
			ps.setString(6, PT.getNotes());
			ps.setInt(7, PT.getUrgent());
			ps.setString(8, PT.getStartTime());
			ps.setString(9, PT.getEndTime());
			ps.setString(10, PT.getMinGrad());
			if(PT.isDSA()) {
				ps.setInt(11, 1);
			}
			else {
				ps.setInt(11, 0);
			}
			if(PT.isDHA()) {
				ps.setInt(12, 1);
			}
			else {
				ps.setInt(12, 0);
			}
			if(PT.isBenCoA()) {
				ps.setInt(13, 1);
			}
			else {
				ps.setInt(13, 0);
			}
			ps.setString(14, us.getEmail());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return PT;

	}
	public ArrayList<NA> getNA(User u){
		ArrayList<NA> output = new ArrayList<NA>();
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "SELECT * FROM NA WHERE USERID = ?";
			PreparedStatement ps =  conn.prepareCall(sql);
			ps.setString(1, u.getEmail());
			ResultSet rs = ps.executeQuery();
			int c = 0;
			while(rs.next()) {
				System.out.println(c);
				c++;
				boolean aproved = false;
				boolean DSA = false;
				boolean DHA = false;
				boolean BenCoA = false;
				int urgent = 0;
				if(rs.getInt(2) == 1) {
					aproved = true;
				}
				if(rs.getInt(11) == 1) {
					DSA = true;
				}
				if(rs.getInt(12) == 1) {
					DHA = true;
				}
				if(rs.getInt(13) == 1) {
					BenCoA = true;
				}
				if(rs.getInt(7) == 1) {
					urgent = 1;
				}
				NA  temp = new NA(rs.getDouble(2),rs.getString(3),rs.getInt(4), rs.getString(6), rs.getString(8), rs.getString(9), rs.getString(10));
				temp.setAproved(aproved);
				temp.setDSA(DSA);
				temp.setDHA(DHA);
				temp.setBenCoA(BenCoA);
				temp.setUrgent(urgent);
				output.add(temp);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return output;
		
	}
	public ArrayList<PendingTransactions> getPT(User u){
		ArrayList<PendingTransactions> output = new ArrayList<PendingTransactions>();
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "SELECT * FROM PT WHERE USERID = ?";
			PreparedStatement ps =  conn.prepareCall(sql);
			ps.setString(1, u.getEmail());
			ResultSet rs = ps.executeQuery();
			int c = 0;
			while(rs.next()) {
				System.out.println(c);
				c++;
				boolean aproved = false;
				boolean DSA = false;
				boolean DHA = false;
				boolean BenCoA = false;
				int urgent = 0;
				if(rs.getInt(2) == 1) {
					aproved = true;
				}
				if(rs.getInt(11) == 1) {
					DSA = true;
				}
				if(rs.getInt(12) == 1) {
					DHA = true;
				}
				if(rs.getInt(13) == 1) {
					BenCoA = true;
				}
				if(rs.getInt(7) == 1) {
					urgent = 1;
				}
				PendingTransactions temp = new PendingTransactions(rs.getDouble(2),rs.getString(3),rs.getInt(4), rs.getString(6), rs.getString(8), rs.getString(9), rs.getString(10));
				temp.setAproved(aproved);
				temp.setDSA(DSA);
				temp.setDHA(DHA);
				temp.setBenCoA(BenCoA);
				temp.setUrgent(urgent);
				output.add(temp);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return output;
		
	}
	public ArrayList<User> getUsers() {
		ArrayList<User> output = new ArrayList<User>();
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "SELECT * FROM EMPLOYEE";
			try {
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				while(rs.next()) {
					User temp = new User(rs.getString(3), rs.getString(1), rs.getString(2), rs.getString(4), rs.getInt(5),  rs.getInt(6), rs.getInt(7), rs.getString(8));
					output.add(temp);

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return output;

}
	public PendingTransactions addPT(ArrayList<User> u, PendingTransactions PT, User us) {
		int newID = 0;
		int flag = 0;
		for(User x : u) {
			for(PendingTransactions y : x.getPendingTransactions()) {
				if(y.getID() >= newID) {
					newID = y.getID();
					flag = 1;
				}
			}
			
		}
		if(flag == 1) {
			newID++;
		}
		PT.setID(newID);
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection();){
			String sql = "INSERT INTO PT VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps =  conn.prepareCall(sql);
			ps.setInt(1, PT.getID());
			ps.setDouble(2, PT.getAmount());
			ps.setString(3, PT.getFileID());
			ps.setInt(4, PT.getType());
			if(PT.isAproved()) {
				ps.setInt(5, 1);
			}
			else {
				ps.setInt(5, 0);
			}
			ps.setString(6, PT.getNotes());
			ps.setInt(7, PT.getUrgent());
			ps.setString(8, PT.getStartTime());
			ps.setString(9, PT.getEndTime());
			ps.setString(10, PT.getMinGrad());
			if(PT.isDSA()) {
				ps.setInt(11, 1);
			}
			else {
				ps.setInt(11, 0);
			}
			if(PT.isDHA()) {
				ps.setInt(12, 1);
			}
			else {
				ps.setInt(12, 0);
			}
			if(PT.isBenCoA()) {
				ps.setInt(13, 1);
			}
			else {
				ps.setInt(13, 0);
			}
			ps.setString(14, us.getEmail());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return PT;

	}
	public void addUser(User u) {
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection();){
			String sql = "INSERT INTO EMPLOYEE (FNAME, LNAME, UNAME, EMAIL, REPORTSTO, JOBID, EMPID, PWD, AOUNTUSED) VALUES(?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps =  conn.prepareCall(sql);
			ps.setString(1, u.getFname());
			ps.setString(2, u.getLname());
			ps.setString(3, u.getUsername());
			ps.setString(4, u.getEmail());
			ps.setInt(5, u.getReportsto());
			ps.setInt(6, u.getJobId());
			ps.setInt(7, u.getEmpId());
			ps.setString(8, u.getPWD());
			ps.setDouble(9, u.getReimbursementUsed());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
