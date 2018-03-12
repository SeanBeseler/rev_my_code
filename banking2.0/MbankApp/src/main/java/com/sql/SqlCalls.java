package com.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.objects.account.Acount;
import com.objects.user.BankUser;
import com.util.ConnectionFactory;

public class SqlCalls {
	public void makeUser(String fname, String lname, String UID, String PWD, String Email) {
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "INSERT INTO USERS VALUES ( ? , ? , ?, ?, ?) ";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, fname);
			pstmt.setString(2, lname);
			pstmt.setString(3, UID);
			pstmt.setString(4, PWD);
			pstmt.setString(5, Email);
			pstmt.executeQuery();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public int makeAcount(String username) {
		int lastId = -1;
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "SELECT MAX(AID) FROM ACOUNTS";
			
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery(sql);
			
			rs.next();
			lastId = rs.getInt(1);
			sql = "INSERT INTO ACOUNTS VALUES ( ? , ? , ?) ";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ++lastId);
			pstmt.setString(2, username);
			pstmt.setDouble(3, 0.0);
			pstmt.executeQuery();
			return lastId;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lastId;
	}
	public void updateAcountBalance(double am, int AID) {
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "UPDATE ACOUNTS SET BAL = ? WHERE AID = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setDouble(1, am);
			pstmt.setString(2, String.valueOf(AID));
			pstmt.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public ArrayList<Acount> getAcount(BankUser user){
		ArrayList<Acount> output = new ArrayList<Acount>();
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "SELECT * FROM ACOUNTS WHERE ? = USID";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUID());
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				Acount temp = new Acount(Integer.parseInt(rs.getString(1)), rs.getDouble(3));
				output.add(temp);
			}
			return output;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return output;
		
	}
	public ArrayList<BankUser> getUsers() {
		ArrayList<BankUser> output = new ArrayList<BankUser>();
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			String sql = "SELECT * FROM USERS";
			try {
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				while(rs.next()) {
					BankUser temp = new BankUser(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
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
	
	public boolean addTest(String name) {
		int value;
		
		try(Connection conn = ConnectionFactory.getInstance().getConnection()){
			
			String sql = "SELECT MAX(TESTID) FROM TEST";
			
			Statement stmt = conn.createStatement();
			
			ResultSet rs = stmt.executeQuery(sql);
			
			rs.next();
			int lastId = rs.getInt(1);
			
			sql = "INSERT INTO TEST VALUES(?, ?, ?)";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, ++lastId);
			System.out.println(lastId);
			pstmt.setString(2, name);
			System.out.println(name);
			pstmt.setInt(3, lastId + 10);
			
			value = pstmt.executeUpdate();
			
			System.out.println(value);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

}
